package org.example.refactoring.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalService {

    private static JustService justService;

    public Object doBusinessLogic(String input) {
        doSomeLogic(input);
        return doParticularLogic();
    }

    @Transactional
    private Object doSomeLogic(String input) {
        Object result = new Object();
        if (input == "goodInput") {
            result = doParticularLogic();
        }
        return result;
    }

    @Transactional
    public Object doParticularLogic() {
        Object result = new Object();
        try {
            justService.callRandomMethod();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

@Service
class CorrectJustService {
    private final JustService justService;

    @Autowired
    public CorrectJustService(JustService justService) {
        this.justService = justService;
    }

    @Transactional
    public Object doBusinessLogic(String input) {
        doSomeLogic(input);
        return doParticularLogic();
    }

    private Object doSomeLogic(String input) {
        Object result = new Object();
        if (input.equals("goodInput")) {
            result = doParticularLogic();
        }
        return result;
    }

    @Transactional
    public Object doParticularLogic() {
        Object result = new Object();
        try {
            justService.callRandomMethod();
        } catch(Exception e) {
            //LOG and catch detailed exception
        }
        return result;
    }
}
