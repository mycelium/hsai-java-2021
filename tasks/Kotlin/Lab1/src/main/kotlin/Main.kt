fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Incorrect argument size")
        return
    }
    val params: GrekParams
    try {
        params = createParams(args)
    } catch (ex: Throwable) {
        println("Can't create grek parameters because of ${ex.message}")
        return
    }
    try {
        Grek().run(params)
    } catch (ex: Throwable) {
        println("Can't run grek because of ${ex.message}")
        return
    }
}
