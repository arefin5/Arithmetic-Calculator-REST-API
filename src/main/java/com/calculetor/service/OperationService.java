package com.calculetor.service;

import com.calculetor.model.Operation;
import com.calculetor.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public Operation performOperation(String type, Integer cost) {
        Operation operation = new Operation();
        operation.setType(type);
        operation.setCost(cost);
        return operationRepository.save(operation);
    }
}

// package com.calculetor.service;

// import com.calculetor.model.Operation;
// import com.calculetor.repository.OperationRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// @Service
// @RequiredArgsConstructor
// public class OperationService {

//     private final OperationRepository operationRepository;

//     public Operation performOperation(String type, Integer cost, User user) {
//         Operation operation = new Operation();
//         operation.setType(type); // Use the passed type
//         operation.setCost(cost); // Use the passed cost
//         operation.setUser(user); // Set the user

//         return operationRepository.save(operation);
//     }
// }
