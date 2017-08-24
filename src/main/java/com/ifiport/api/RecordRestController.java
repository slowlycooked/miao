package com.ifiport.api;

import com.ifiport.model.Record;
import com.ifiport.pojo.RecordSum;
import com.ifiport.service.RecordService;
import com.ifiport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class RecordRestController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;


    public RecordRestController(RecordService recordRepository){
        this.recordService = recordRepository;

    }

    @RequestMapping(path="/api/records", method=RequestMethod.GET)
    public List<Record> getAllRecords(){
        return recordService.getAllRecordsOrderByDate();
    }

    @RequestMapping(path="/api/records/date", method=RequestMethod.GET)
    public List<Record> getAllRecordsByDate(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date1){
        Calendar c = Calendar.getInstance();
        c.setTime(date1);

        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date date2 = c.getTime();
        return recordService.getRecordsByDate(date1,date2);
    }


    @RequestMapping(value = "/api/records/{id}", method = RequestMethod.GET)
    public Record getRecordById(@PathVariable("id") long id){
        return recordService.getRecordById(id);
    }

    @RequestMapping(value = "/api/records", method = RequestMethod.POST)
    public @ResponseBody String addRecord(@RequestBody Record record){
        //CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Record newRecord = new Record();
        newRecord.setType(record.getType());
        newRecord.setAmount(record.getAmount());
        newRecord.setCreator(userService.getUser("user1"));
        newRecord.setRecordDate1(record.getRecordDate1());
        Date date2 = record.getRecordDate2() == null ? record.getRecordDate1() : record.getRecordDate2();
        newRecord.setRecordDate2(date2);
        newRecord.setNotes(record.getNotes());
        recordService.insert(newRecord);
        return "200";

    }

    @RequestMapping(value = "/api/records/del/{id}", method = RequestMethod.GET)
    public @ResponseBody String  deleteById(@PathVariable("id") long id){
         recordService.deleteById(id);
         return "200";
    }

    @RequestMapping(path="/api/records/sum", method=RequestMethod.GET)
    public List<RecordSum> getRecordsSum(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date date1){
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date1);

        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        Date date2 = c.getTime();
      return recordService.getRecordsSummaryByDate(date1, date2);
    }


}