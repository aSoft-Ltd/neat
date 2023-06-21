package neat

typealias ValidationFactory<R> = Validators<R>.() -> Validator<R>