package com.learning.serverapp.resource;

import com.learning.serverapp.enumeration.Status;
import com.learning.serverapp.model.Response;
import com.learning.serverapp.model.Server;
import com.learning.serverapp.service.implementation.ServerserviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerserviceImpl serverService;

    // getting the servers list
    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .StatusCode(OK.value())
                        .message("servers retrieved successfully")
                        .data(Map.of("servers",serverService.list(30)))
                        .build()
        );
    }

    // pinging a server
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
            Response.builder()
                    .timeStamp(now())
                    .status(OK)
                    .StatusCode(OK.value())
                    .message(server.getStatus()== Status.SERVER_UP ? "Ping success":"Ping failed")
                    .data(Map.of("server",server))
                    .build()
        );
    }

    // create a new server
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(CREATED)
                        .StatusCode(CREATED.value())
                        .message("Server created successfully")
                        .data(Map.of("server",serverService.create(server)))
                        .build()
        );
    }

    // get a server by his id
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .StatusCode(OK.value())
                        .message("Server retrieved")
                        .data(Map.of("server",serverService.get(id)))
                        .build()
        );
    }

    // delete a server by his id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(OK)
                        .StatusCode(OK.value())
                        .message("Server deleted")
                        .data(Map.of("deleted",serverService.delete(id)))
                        .build()
        );
    }

    // get an image from the server
    @GetMapping(path="/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Dowloads/images"+imageName));
    }

}
