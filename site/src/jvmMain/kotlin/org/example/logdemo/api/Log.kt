package org.example.logdemo.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext

@Api
fun log(ctx: ApiContext) {
    val logText = ctx.req.body?.decodeToString() ?: return
    ctx.logger.debug("Logged by client: $logText")
}
