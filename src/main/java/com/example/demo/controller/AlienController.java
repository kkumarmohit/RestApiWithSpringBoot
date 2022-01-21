package com.example.demo.controller;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AlienController {
    @Autowired
    AlienRepo repo;

    @RequestMapping("/home")
    public String start()
    {
        return "home";
    }

    @RequestMapping("/addalien")
    public String addalien(Alien alien)
    {
        repo.save(alien);
        return "home";
    }

    //GET Request
    @RequestMapping("/getalien")
    public ModelAndView getalien(@RequestParam int aid)
    {
        ModelAndView mv = new ModelAndView("showAlien.html");
        Alien alien = repo.findById(aid).orElse(null);
        System.out.println(repo.findByTech("java"));
        System.out.println(repo.findByAidGreaterThan(101));
        System.out.println(repo.findByTechSorted("java"));
        mv.addObject(alien);
        return mv;
    }

    //REST URL
    @RequestMapping("/aliens")
    @ResponseBody //to let spring know that this is NOT a view but data.
    public String getAliens(){
        return repo.findAll().toString();
    }

    //REST URL
    @RequestMapping("/alien/{aid}")
    @ResponseBody //to let spring know that this is NOT a view but data.
    public String getAlien(@PathVariable int aid){ //PathVariable is different from RequestParams for obvious reasons
        return repo.findById(aid).toString();
    }

    //REST URL POST
    @PostMapping("/alien") //@PostMapping("/alien",consumes="application/xml") - To restrict what kind of data to accept
    @ResponseBody
    public Alien addAlien(@RequestBody Alien alien){
        repo.save(alien);
        return alien;
    }

    //REST URL WITH JSON RESPONSE
    @RequestMapping("/jsonaliens") //TO RESTRICT TYPE OF DATA RESPONSE: @RequestMapping(path="/jsonaliens",produces="{application/xml}")
    @ResponseBody //to let spring know that this is NOT a view but data.
    public List<Alien> getJsonAliens(){
        return repo.findAll();
    }

    //REST URL WITH JSON RESPONSE (The conversion is handled by jackson dependency
    @RequestMapping("/jsonalien/{aid}")
    @ResponseBody //to let spring know that this is NOT a view but data.
    public Optional<Alien> getJsonAlien(@PathVariable int aid){ //PathVariable is different from RequestParams for obvious reasons
        return repo.findById(aid);
    }

    //REST URL DELETE
    @DeleteMapping("/alien/{aid}")
    @ResponseBody
    public Alien deleteAlien(@PathVariable int aid){
        Alien alien = repo.findById(aid).orElse(new Alien());
        repo.delete(alien);
        return alien;
    }

    //REST URL PUT
    @PutMapping("/alien") //@PutMapping("/alien",consumes="application/xml") - To restrict what kind of data to accept
    @ResponseBody
    public Alien updateAlien(@RequestBody Alien alien){
        repo.save(alien);
        return alien;
    }
}
