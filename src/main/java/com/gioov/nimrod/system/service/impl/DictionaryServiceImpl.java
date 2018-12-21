package com.gioov.nimrod.system.service.impl;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.office.ExcelUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcel;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcelUtil;
import com.gioov.nimrod.system.entity.DictionaryEntity;
import com.gioov.nimrod.system.mapper.DictionaryMapper;
import com.gioov.nimrod.system.service.DictionaryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 添加字典到 ModelAndView
     *
     * @param modelAndView ModelAndView
     */
    public void addDictionaryToModelAndView(ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addAllObjects(keyValueMap());
        }
    }

    @Override
    public void addDictionaryToServletContext() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        if (servletContext != null) {
            List<DictionaryEntity> dictionaryEntityList = dictionaryMapper.listAll();
            if (dictionaryEntityList != null) {
                for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                    servletContext.setAttribute(dictionaryEntity.getKey().toUpperCase() + "." + dictionaryEntity.getValueSlug().toUpperCase(), dictionaryEntity.getValue());
                }
                Map<String, List<DictionaryEntity>> dictionaryEntityMap = new HashMap<>(6);
                for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                    String key = dictionaryEntity.getKey().toUpperCase();
                    if (dictionaryEntityMap.containsKey(key)) {
                        List<DictionaryEntity> dictionaryEntityList1 = dictionaryEntityMap.get(key);
                        if (!dictionaryEntityList1.contains(dictionaryEntity)) {
                            dictionaryEntityList1.add(dictionaryEntity);
                            dictionaryEntityMap.put(key, dictionaryEntityList1);
                        }
                    } else {
                        List<DictionaryEntity> dictionaryEntityList2 = new ArrayList<>(1);
                        dictionaryEntityList2.add(dictionaryEntity);
                        dictionaryEntityMap.put(key, dictionaryEntityList2);
                    }
                }
                for (Map.Entry entry : dictionaryEntityMap.entrySet()) {
                    servletContext.setAttribute((String) entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Override
    public Object getFromDatabase(String key, String valueSlug) {
        DictionaryEntity dictionaryEntity = dictionaryMapper.getOneByKeyAndValueSlug(key.toUpperCase(), valueSlug.toUpperCase());
        if(dictionaryEntity != null) {
            return dictionaryEntity.getValue();
        }
        return null;
    }

    private Object getValueByKeyAndValueSlug(String key, String valueSlug) {
        ServletContext servletContext = webApplicationContext.getServletContext();
        if (servletContext != null) {
            return servletContext.getAttribute(key + "." + valueSlug);
        } else {
            return null;
        }
    }

    @Override
    public Object get(String key, String valueSlug) {
        return getValueByKeyAndValueSlug(key, valueSlug);
    }

    @Override
    public Object get(String key, String valueSlug, Object defaultValue) {
        Object v = getValueByKeyAndValueSlug(key, valueSlug);
        if (v != null) {
            return v;
        }
        return defaultValue;
    }

    @Override
    public Map<String, Map<String, Object>> keyValueMap() {
        Map<String, Map<String, Object>> mapMap = new HashMap<>(6);
        List<DictionaryEntity> dictionaryEntityList = dictionaryMapper.listAll();
        if (dictionaryEntityList != null) {
            for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                if (mapMap.containsKey(dictionaryEntity.getKey())) {
                    Map<String, Object> valueMap = mapMap.get(dictionaryEntity.getKey());
                    if (!valueMap.containsKey(dictionaryEntity.getValueSlug())) {
                        valueMap.put(dictionaryEntity.getValueSlug(), dictionaryEntity.getValue());
                    }
                    mapMap.put(dictionaryEntity.getKey(), valueMap);
                } else {
                    Map<String, Object> valueMap = new HashMap<>(1);
                    valueMap.put(dictionaryEntity.getValueSlug(), dictionaryEntity.getValue());
                    mapMap.put(dictionaryEntity.getKey(), valueMap);
                }
            }
        }
        return mapMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DictionaryEntity> listAllByKey(String key) {
        return (List<DictionaryEntity>) Objects.requireNonNull(webApplicationContext.getServletContext()).getAttribute(key.toUpperCase());
    }

    @Override
    public Pagination.Result<DictionaryEntity> pageAllByDictionaryCategoryId(Long dictionaryCategoryId, Integer page, Integer rows) {
        List<DictionaryEntity> dictionaryEntityList;
        Pagination.Result<DictionaryEntity> paginationResult = new Pagination().new Result<>();
        dictionaryEntityList = dictionaryMapper.pageAllByDictionaryCategoryId(dictionaryCategoryId, new Pageable(page, rows));
        if (dictionaryEntityList != null) {
            paginationResult.setRows(dictionaryEntityList);
        }
        int count = dictionaryMapper.countAllByDictionaryCategoryId(dictionaryCategoryId);
        paginationResult.setTotal(count);
        return paginationResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryEntity insertOne(DictionaryEntity dictionaryEntity) {
        DictionaryEntity dictionaryEntity1 = new DictionaryEntity();
        Date date = new Date();
        dictionaryEntity1.setKey(dictionaryEntity.getKey().toUpperCase());
        dictionaryEntity1.setKeyName(dictionaryEntity.getKeyName());
        dictionaryEntity1.setValueName(dictionaryEntity.getValueName());
        dictionaryEntity1.setValueSlug(dictionaryEntity.getValueSlug().toUpperCase());
        dictionaryEntity1.setValue(dictionaryEntity.getValue());
        dictionaryEntity1.setEditable(dictionaryEntity.getEditable());
        dictionaryEntity1.setDictionaryCategoryId(dictionaryEntity.getDictionaryCategoryId());
        dictionaryEntity1.setSort(dictionaryEntity.getSort());
        dictionaryEntity1.setRemark(dictionaryEntity.getRemark());
        dictionaryEntity1.setGmtModified(date);
        dictionaryEntity1.setGmtCreated(date);
        dictionaryMapper.insertOne(dictionaryEntity1);
        return dictionaryEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DictionaryEntity updateOne(DictionaryEntity dictionaryEntity) {
        DictionaryEntity dictionaryEntity1 = dictionaryMapper.getOne(dictionaryEntity.getId());
        Date date = new Date();
        dictionaryEntity1.setKey(dictionaryEntity.getKey().toUpperCase());
        dictionaryEntity1.setKeyName(dictionaryEntity.getKeyName());
        dictionaryEntity1.setValueName(dictionaryEntity.getValueName());
        dictionaryEntity1.setValueSlug(dictionaryEntity.getValueSlug().toUpperCase());
        dictionaryEntity1.setValue(dictionaryEntity.getValue());
        dictionaryEntity1.setEditable(dictionaryEntity.getEditable());
        dictionaryEntity1.setSort(dictionaryEntity.getSort());
        dictionaryEntity1.setRemark(dictionaryEntity.getRemark());
        dictionaryEntity1.setGmtModified(date);
        dictionaryMapper.updateOne(dictionaryEntity1);
        return dictionaryEntity1;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int deleteAll(List<Long> idList) {
        return dictionaryMapper.deleteAll(idList);
    }

    @Override
    public DictionaryEntity getOne(Long id) {
        return dictionaryMapper.getOne(id);
    }

    @Override
    public void exportAllByDictionaryCategoryIdList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<Long> idList) throws BaseResponseException {
        List<DictionaryEntity> dictionaryEntityList = new ArrayList<>();
        for (Long id : idList) {
            dictionaryEntityList.addAll(dictionaryMapper.listAllByDictionaryCategoryId(id));
        }
        String filename = "数据字典_" + com.gioov.common.util.DateUtil.getNow("yyyyMMddHHmmss") + ".xls";
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row row = sheet.createRow(0);
            int fieldIndex = 0;
            Field[] fields = DictionaryEntity.class.getDeclaredFields();
            for (Field field : fields) {
                ExportByExcel annotation = field.getAnnotation(ExportByExcel.class);
                if (annotation != null) {
                    String name = annotation.name();
                    if ("".equals(name)) {
                        name = annotation.value();
                    }
                    Cell cell1 = row.createCell(fieldIndex);
                    cell1.setCellValue(name);
                    fieldIndex++;
                }
            }
            int rowIndex = 1;
            for (DictionaryEntity dictionaryEntity : dictionaryEntityList) {
                row = sheet.createRow(rowIndex);
                ExportByExcelUtil.exportFieldValue(row, dictionaryEntity);
                rowIndex++;
            }
            ExcelUtil.read2003AndDownloadExportExcel(httpServletRequest, httpServletResponse, workbook, filename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("导出失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void importAllByDictionaryCategoryId(MultipartFile multipartFile, Long categoryId) throws BaseResponseException {
        try {
            List<Map<Integer, Object>> list = uploadAndReadExcel(multipartFile);
            list.remove(0);
            for (Map<Integer, Object> map : list) {
                DictionaryEntity dictionaryEntity = new DictionaryEntity();
                dictionaryEntity.setDictionaryCategoryId(categoryId);
                DictionaryEntity dictionaryEntity1 = ExportByExcelUtil.entityAssignValue(DictionaryEntity.class, dictionaryEntity, map);
                int effectRows = dictionaryMapper.insertOne(dictionaryEntity1);
                if (effectRows <= 0) {
                    throw new BaseResponseException("导入失败");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseResponseException("导入失败");
        }

    }

    private List<Map<Integer, Object>> uploadAndReadExcel(MultipartFile multipartFile) throws IOException {
        List<Map<Integer, Object>> list = new ArrayList<>();
        Workbook workbook = ExcelUtil.getWorkbook(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile.getInputStream());
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex;
            for (rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                int cellIndex;
                Map<Integer, Object> map = new HashMap<>(1);
                for (cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    map.put(cellIndex, row.getCell(cellIndex));
                }
                list.add(map);
            }
        }
        return list.isEmpty() ? null : list;
    }

}
