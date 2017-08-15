package com.ifiport.api;

import com.ifiport.config.CustomUserDetails;
import com.ifiport.model.Record;
import com.ifiport.service.RecordService;
import com.ifiport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return recordService.getAllRecords();
    }

    @RequestMapping(value = "/api/records/{id}", method = RequestMethod.GET)
    public Record getRecordById(@PathVariable("id") long id){
        return recordService.getRecordById(id);
    }

    @RequestMapping(value = "/api/records", method = RequestMethod.POST)
    public @ResponseBody String addRecord(@RequestBody Record record){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Record newRecord = new Record();
        newRecord.setType(record.getType());
        newRecord.setAmount(record.getAmount());
        newRecord.setCreator(userService.getUser(userDetails.getUsername()));
        recordService.insert(newRecord);
        return "saved";

    }

    @RequestMapping(value = "/api/records/del/{id}", method = RequestMethod.GET)
    public @ResponseBody String  deleteById(@PathVariable("id") long id){
         recordService.deleteById(id);
         return "deleted";
    }

//    @RequestMapping(value = "/api/user/{username}", method = RequestMethod.GET)
//    public List<Record> getRecordByUser(@PathVariable String username){
//        return  recordService.findByUser(userService.getUser(username));
//    }

}