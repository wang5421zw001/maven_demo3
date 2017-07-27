package com.hmz.controller;

import com.hmz.po.Tree;
import com.hmz.po.User;
import com.hmz.repository.TreeRepository;
import com.hmz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by 王瑆 on 2017/7/8.
 */
@Controller
public class UserAction {
@Autowired
    private UserRepository userRepository;
@Autowired
    private TreeRepository treeRepository;
    @RequestMapping("/add")
  public String add(User user){
      userRepository.save(user);
       return "redirect:select";
    }
  @RequestMapping("/select")
  public String select(Model model){
      Sort sort = new Sort(Sort.Direction.ASC, "userId");
    List<User> users = userRepository.findAll(sort); 
    model.addAttribute("users",users);
    return "register";
  }
    @RequestMapping("/findUserByName")
  public void findUserByName(String userName,HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
      try {
          PrintWriter writer = response.getWriter();
         User user = userRepository.findByUserName(userName);
         System.out.print(user);
          if(user!=null){
                 writer.print("2");
          }
          else{
              writer.print("1");
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
    @RequestMapping("/deleteUserById")
  public String delete(int userId){
        User u=new User();
        u.setUserId(userId);
       userRepository.delete(u);
     return "redirect:select";
  }
    @RequestMapping("/findUserById")
    @ResponseBody
  public User findUserById(int userId){
        User user = userRepository.findByUserId(userId);
        return user;
  }
  @RequestMapping("/exportMsg")
  public void exportUser(String ids) throws Exception{
      List<User> userList = userRepository.findAll(ids);
      PrintStream ps=new PrintStream(new FileOutputStream("f://msg.txt"),true);
  for ( int i=0;i<userList.size();i++){
       User u =userList.get(i);
         ps.println(u.getUserId()+"/"+u.getUserName()+"/"+u.getPassword()+"/"+u.getUserMoney()+"/"+u.getEmail()
                 +"/"+u.getGender());
  }
  ps.flush();
      ps.close();
  }
  @RequestMapping("/gotoIndex")
  public String gogo(){
      return "index";
  }
  @RequestMapping("/zTree")
  @ResponseBody
  public List<Tree> showTree(){
    return treeRepository.findAll();
  }
}
