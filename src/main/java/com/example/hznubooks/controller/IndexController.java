package com.example.hznubooks.controller;

import com.example.hznubooks.entity.Book;
import com.example.hznubooks.entity.Record;
import com.example.hznubooks.entity.User;
import com.example.hznubooks.service.BookService;
import com.example.hznubooks.service.RecordService;
import com.example.hznubooks.service.UserService;
import com.example.hznubooks.utils.md5tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    RecordService recordService;
    md5tool md5tool;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/main")
    public String main(HttpSession session) {
        if (session.getAttribute("username") != null)
            return "main";
        else {
            return "index";
        }
    }


    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session)
    {
        try{
            //如果用户名和密码正确
            password = md5tool.md5Encode(password);
            if (userService.login(username,password)) {
                int type = userService.getInfoByUsername(username).getType();
                session.setAttribute("type", type);
                session.setAttribute("username", username);
                return "redirect:/main";
            }
            else {
                model.addAttribute("msg","用户名或密码错误");
                return "index";//跳转到首页
            }
        }
        catch(Exception e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";//跳转到首页
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
    @RequestMapping("userInfo")
    @ResponseBody
    public User userInfo(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return userService.getInfoByUsername(username);
    }
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public int updateInfo(@RequestParam("name") String name,
                          @RequestParam("phone") String phone,
                          HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userService.getInfoByUsername(username);
        userService.updateUser(username,user.getPassword(), user.getName(), phone,user.getType(),user.getNumber());
        return 1;
    }
    @RequestMapping("getUsers")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @RequestMapping("getBooks")
    @ResponseBody
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    @RequestMapping("getRecords")
    @ResponseBody
    public List<Record> getRecords() {
        return recordService.getRecords();
    }
    @RequestMapping("getUsersByType")
    @ResponseBody
    public List<User> getUsersByType(@RequestParam("type") int type) {
        return userService.getUsersByType(type);
    }
    @RequestMapping("getRecordsByUsername")
    @ResponseBody
    public List<Record> getRecordsByUsername(HttpSession session) {
        return recordService.getRecordByUsername((String) session.getAttribute("username"));
    }
    @RequestMapping("getRecordsByInputUsername")
    @ResponseBody
    public List<Record> getRecordsByInputUsername(@RequestParam String username) {
        return recordService.getRecordByUsername(username);
    }
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public int addUser(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name,
                       @RequestParam("phone") String phone,
                       @RequestParam("type") int type,
                       @RequestParam("number") int number
                       ) throws Exception {
        userService.addUser(username, md5tool.md5Encode(password), name, phone, type, number);
        return 1;
    }
    @RequestMapping(value = "delUser")
    @ResponseBody
    public int delUser(@RequestParam("userid") int userid) {
        userService.delUser(userid);
        return 1;
    }
    @RequestMapping(value = "delBook")
    @ResponseBody
    public int delBook(@RequestParam("bookid") int bookid) {
        bookService.delBook(bookid);
        return 1;
    }
    @RequestMapping(value = "addBook", method = RequestMethod.POST)
    @ResponseBody
    public int addBook(@RequestParam("bookname") String bookname,
                       @RequestParam("author") String author,
                       @RequestParam("publisher") String publisher,
                       @RequestParam("category") String category,
                       @RequestParam("status") int status
    ) {
        bookService.addBook(bookname, author, publisher, category, status);
        return 1;
    }
    @RequestMapping(value = "getInfo", method = RequestMethod.POST)
    @ResponseBody
    public User getInfo(@RequestParam("username") String username) {
        return userService.getInfoByUsername(username);
    }
    @RequestMapping(value = "updateUserNum", method = RequestMethod.POST)
    @ResponseBody
    public int updateUserNum(@RequestParam String username) {
        User user = userService.getInfoByUsername(username);
        userService.updateUser(username,user.getPassword(), user.getName(), user.getPhone(),user.getType(),user.getNumber()-1);
        return 1;
    }
    @RequestMapping(value = "updateUserNum2", method = RequestMethod.POST)
    @ResponseBody
    public int updateUserNum2(@RequestParam String username) {
        User user = userService.getInfoByUsername(username);
        userService.updateUser(username,user.getPassword(), user.getName(), user.getPhone(),user.getType(),user.getNumber()+1);
        return 1;
    }
    //还书
    @RequestMapping(value = "updateBook", method = RequestMethod.POST)
    @ResponseBody
    public int updateBook(@RequestParam int bookid) {
        bookService.returnBook(bookid);
        return 1;
    }
    //借书
    @RequestMapping(value = "updateBook2", method = RequestMethod.POST)
    @ResponseBody
    public int updateBook2(@RequestParam int bookid) {
        bookService.borrowBook(bookid);
        return 1;
    }
    @RequestMapping(value = "updateRecord", method = RequestMethod.POST)
    @ResponseBody
    public int updateRecord(@RequestParam int recordid, @RequestParam float price) {
        recordService.updateRecord(recordid, price);
        return 1;
    }
    @RequestMapping(value = "addRecord", method = RequestMethod.POST)
    @ResponseBody
    public int addRecord(@RequestParam String username,@RequestParam int bookid) {
        recordService.addRecord(username, bookid);
        return 1;
    }

}
