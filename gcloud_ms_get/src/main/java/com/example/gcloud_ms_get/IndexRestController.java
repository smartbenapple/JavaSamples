package com.example.gcloud_ms_get;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;


@RestController
public class IndexRestController
{
    @PostMapping("/hello") // in: maps path.
    @ResponseBody // out: deserializes all public vars n props
    public ResultMessage Root(@RequestBody RootMessage rootmessage)
    {
       return new ResultMessage(rootmessage.getMessage());
    }

    @GetMapping("/getpayroll") // in: maps path
    @JsonView(PayrollItem.JsonType.class) // out: deserializes only decorated @JsonView(filter) items.
    public PayrollItem GetPayroll()
    {
        return new PayrollItem();
    }

    // when to use (name= "name") pattern: https://www.baeldung.com/spring-request-param
    @GetMapping("/get")
    @ResponseBody // out: deserializes all public vars n props
    public String GetData(@RequestParam(name = "name") String firstName)
    {
        return "Hello from server. =" + firstName;
    }

    @GetMapping("/movies")
    @ResponseBody // out: deserializes all public vars n props
    public MovieItem[] GetData()
    {
        getData getData = new getData();
        Movies movies = getData.GetData();
        return movies.GetMovies();
    }

    @PostMapping("/get")
    public void GetDataPost()
    {

    }
}
