// package com.calculetor.controller;

// import com.calculetor.model.Operation;
// import com.calculetor.service.OperationService;
// import com.calculetor.utils.OperationRequest; // Import the OperationRequest class
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

package com.calculetor.controller;

import com.calculetor.model.Operation;
import com.calculetor.model.User; // Ensure User is imported
import com.calculetor.repository.UserRepository;
import com.calculetor.service.OperationService;
import com.calculetor.utils.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/auth/user/{userId}/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/addoparetin")
    public ResponseEntity<String> add(
            @PathVariable("userId") Integer userId,
            @RequestBody OperationRequest operationRequest) {

        Operation operation = operationService.performOperation(
                operationRequest.getType(),
                operationRequest.getCost()
        );

        return ResponseEntity.ok("Operation saved: " + operation.getType());
    }
}
