package com.a4z0.rubrum.interfaces;

import com.a4z0.rubrum.enums.Task;

public interface Request {

    /**
    * @param T a {@link Task}
    * @param Objects requested objects.
    */

    void Run(Task T, Object... Objects);

};