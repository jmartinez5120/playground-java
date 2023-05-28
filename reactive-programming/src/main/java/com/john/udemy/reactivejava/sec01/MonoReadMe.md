# Mono Reactive

| Type              | Condition                                                 | What to use                                                                    |
|:------------------|:----------------------------------------------------------|:-------------------------------------------------------------------------------|
| Create Mono       | Data already present                                      | Mono.just(data)                                                                |
| Create Mono       | Data to be calculated                                     | * Mono.fromSupplier(() -> getData())<br/> * Mono.fromCallable(() -> getData()) |
| Create Mono       | Data is coming from async completableFuture               | Mono.fromFuture(future)                                                        |
| Create Mono       | Emit empty once a given runnable is completed             | Mono.fromRunnable(runnable)                                                    | 
| Pass Mono as arg. | Function accepts a Mono<Address>. But I do not have data. | Mono.empty()                                                                   |
| Return Mono       | Function needs to return a Mono.                          | * Mono.error(...)<br/> * Mono.empty() <br/> * above creation types             | 
