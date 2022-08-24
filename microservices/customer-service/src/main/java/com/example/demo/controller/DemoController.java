package com.example.demo.controller;
import com.example.demo.jpa.Memo;
import com.example.demo.jpa.MemoEntity;
import com.example.demo.jpa.MemoMapper;
import com.example.demo.jpa.MemoRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.scheduler.Scheduler;
import java.util.function.Supplier;
import org.reactivestreams.Publisher;
import java.util.List;

// @CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/demo")
public class DemoController {
    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private final MemoMapper mapper;

    @Autowired
    private final MemoRepository repository;

    @Autowired
    private final Scheduler scheduler;

    @Autowired
    public DemoController(MemoRepository repository, MemoMapper mapper, Scheduler scheduler){
        this.repository = repository;
        this.mapper = mapper;
        this.scheduler = scheduler;
    }
	
    @GetMapping(value = "/")
	String home(){
		return "Hello World!";
	}

    @GetMapping(value="/memos")
    public Flux<Memo> getAllMemos() {
        // return Flux.fromIterable(getMemos());
        return asyncFlux(() -> Flux.fromIterable(getMemos()));
    }

    // @GetMapping(value="/memo/{id}", produces="application/json")
    // public Mono<Memo> getMemoById(@PathVariable("id") long id) {
    //     return repository.findById(id).map(e -> mapper.entityToApi(e));
    // }
    
    @PostMapping(value="/memo", consumes="application/json")
    public Memo createMemo(@RequestBody Memo memo) {
        MemoEntity entity = mapper.apiToEntity(memo);
        Mono<Memo> newEntity = Mono.just(mapper.entityToApi(repository.save(entity)));
        return newEntity.block();
    }

    protected Iterable<Memo> getMemos() {
    // protected List<Memo> getMemos() {
        // List<MemoEntity> entityList = repository.findAll();
        // List<Memo> list = mapper.entityListToApiList(entityList);
        Iterable<MemoEntity> entityList = repository.findAll();
        Iterable<Memo> list = mapper.entityListToApiList(entityList);
        return list;
    }
    
    private <T> Flux<T> asyncFlux(Supplier<Publisher<T>> publisherSupplier) {
        return Flux.defer(publisherSupplier).subscribeOn(scheduler);
    }
//   @PutMapping("/memos/{id}")
//   public ResponseEntity<Memo> updateMemo(@PathVariable("id") long id, @RequestBody Memo memo) {
//     ...
//   }
//   @DeleteMapping("/memos/{id}")
//   public ResponseEntity<HttpStatus> deleteMemo(@PathVariable("id") long id) {
//     ...
//   }
//   @DeleteMapping("/memos")
//   public ResponseEntity<HttpStatus> deleteAllMemos() {
//     ...
//   }
//   @GetMapping("/memos/memotext")
//   public ResponseEntity<List<Memo>> findByMemoText() {
//     ...
}