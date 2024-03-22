package com.example.gcloud_ms_get;

// Testing Shows;
// @ResponseBody will serialize some public variable or public get().
public class ResultMessage
{
    private String _resultMessage = "";
    private String _orgMessage = "";

    public ResultMessage(String message)
    {
        _orgMessage = message;
        _resultMessage = "Hello from server mr." + message;
    }

    // Matches: Json output: {"resultMessage"}
    public String get_resultMessage()
    {
        return _resultMessage;
    }
}
