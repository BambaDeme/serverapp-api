package com.learning.serverapp.service;

import com.learning.serverapp.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    // create methods
    Server create(Server server);

    // read methods
    Collection<Server> list(int limit);
    Server get(Long id);

    // update methods
    Server update(Server server);

    //delete methods
    Boolean delete(Long id);

    Server ping(String ipAddress) throws IOException;

}
