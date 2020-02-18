package co.anbora.labs.engvid.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/addon")
public class AddonController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}