package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.service.WorkSpaceService;
import com.sky.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkSpaceService workSpaceService;

    /**
     * 营业额统计接口
     *
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        TurnoverReportVO turnoverReportVO = new TurnoverReportVO();
        List<LocalDate> dateList = getDateList(begin, end);
        turnoverReportVO.setDateList(StringUtils.join(dateList, ","));

        List<Double> amountList = new ArrayList<>();
        for (LocalDate date : dateList) {
            Double amount = orderMapper.sumByDate(date);
            if (amount == null) {
                amount = 0D;
            }
            amountList.add(amount);
        }
        turnoverReportVO.setTurnoverList(StringUtils.join(amountList, ","));
        return turnoverReportVO;
    }

    /**
     * 用户统计
     *
     * @param begin
     * @param end
     * @return
     */
    @Override
    public UserReportVO getUserReport(LocalDate begin, LocalDate end) {
        UserReportVO userReportVO = new UserReportVO();
        List<LocalDate> dateList = getDateList(begin, end);
        userReportVO.setDateList(StringUtils.join(dateList, ","));

        List<Integer> countList = new ArrayList<>();
        for (LocalDate date : dateList) {
            Integer count = userMapper.countUserByDate(date);
            countList.add(count);
        }
        userReportVO.setTotalUserList(StringUtils.join(countList, ","));

        List<Integer> newUserList = new ArrayList<>();
        for (int i = 1; i < countList.size(); i++) {
            newUserList.add(countList.get(i) - countList.get(i - 1));
        }
        userReportVO.setNewUserList(StringUtils.join(newUserList, ","));
        return userReportVO;
    }

    /**
     * 订单统计
     *
     * @param begin
     * @param end
     * @return
     */
    @Override
    public OrderReportVO OrdersStatistics(LocalDate begin, LocalDate end) {
        OrderReportVO orderReportVO = new OrderReportVO();
        List<LocalDate> dateList = getDateList(begin, end);
        orderReportVO.setDateList(StringUtils.join(dateList, ","));

        Integer total = 0;
        List<Integer> totalList = new ArrayList<>();
        for (LocalDate date : dateList) {
            Integer count = orderMapper.countByDate(date);
            totalList.add(count);
            total += count;
        }
        orderReportVO.setOrderCountList(StringUtils.join(totalList, ","));
        orderReportVO.setTotalOrderCount(total);

        Integer validOrder = 0;
        List<Integer> validList = new ArrayList<>();
        for (LocalDate date : dateList) {
            Integer count = orderMapper.countValidByDate(date);
            totalList.add(count);
            validOrder += count;
        }
        orderReportVO.setValidOrderCountList(StringUtils.join(validList, ","));
        orderReportVO.setValidOrderCount(validOrder);

        Double completionRate = total * 1.0 / validOrder;
        orderReportVO.setOrderCompletionRate(completionRate);

        return orderReportVO;
    }

    /**
     * 查询销量排名top10接口
     *
     * @param begin
     * @param end
     * @return
     */
    @Override
    public SalesTop10ReportVO top10(LocalDate begin, LocalDate end) {
        List<GoodsSalesDTO> list = orderMapper.getTop10(begin, end);
        SalesTop10ReportVO salesTop10ReportVO = new SalesTop10ReportVO();
        List<String> nameList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (GoodsSalesDTO goodsSalesDTO : list) {
            nameList.add(goodsSalesDTO.getName());
            countList.add(goodsSalesDTO.getNumber());
        }
        salesTop10ReportVO.setNameList(StringUtils.join(nameList, ","));
        salesTop10ReportVO.setNumberList(StringUtils.join(countList, ","));
        return salesTop10ReportVO;
    }

    /**
     * 导出Excel报表接口
     */
    @Override
    public void exportBusinessData(HttpServletResponse response) {
        LocalDate begin = LocalDate.now().plusDays(-30);
        LocalDate end = LocalDate.now();
        BusinessDataVO businessDataVO = workSpaceService.businessData(begin, end);

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheetAt(0);
            sheet.getRow(1).getCell(1).setCellValue("时间：" + begin + "-" + end);

            XSSFRow row = sheet.getRow(3);
            row.getCell(2).setCellValue(businessDataVO.getTurnover());
            row.getCell(4).setCellValue(businessDataVO.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessDataVO.getNewUsers());

            row = sheet.getRow(5);
            row.getCell(2).setCellValue(businessDataVO.getValidOrderCount());
            row.getCell(4).setCellValue(businessDataVO.getUnitPrice());


            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                businessDataVO = workSpaceService.businessData(date.plusDays(-1), date);

                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessDataVO.getTurnover());
                row.getCell(3).setCellValue(businessDataVO.getValidOrderCount());
                row.getCell(4).setCellValue(businessDataVO.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessDataVO.getUnitPrice());
                row.getCell(6).setCellValue(businessDataVO.getNewUsers());
            }

            ServletOutputStream outputStream = response.getOutputStream();
            excel.write(outputStream);

            outputStream.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LocalDate> getDateList(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList();
        dateList.add(begin);
        while (!begin.equals(end)) {
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        return dateList;
    }
}
