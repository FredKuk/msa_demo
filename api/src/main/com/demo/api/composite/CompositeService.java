package co.demo.api.composite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(description = "통합서비스 REST API")
public interface CompositeService {
    /**
     * Sample usage:
     *
     * curl -X POST $HOST:$PORT/composite \
     *   -H "Content-Type: application/json" --data \
     *   '{"cusId":01235334,"socialId":"8907071234567","name":"홍길동"}'
     *
     * @param body
     */
    @ApiOperation(
        value = "${api.composite.create-user.description}",
        notes = "${api.composite.create-user.notes}")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
        @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    @PostMapping(
        value    = "/join",
        consumes = "application/json")
    void createUser(@RequestBody UserAggregate body);


    // @ApiOperation(value = "${api.composite.get-composite-customer.description}", notes = "${api.composite.get-composite-customer.notes}")
    // @ApiResponses(value = {
    //     @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
    //     @ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
    //     @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    // })
    // @GetMapping(value="/customer-composite/{cusId}", produces = "application/json")
    // Mono<ProductAggregate> getCompositeCustomer(@PathVariable String cusId);
}