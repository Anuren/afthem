package com.apifortress.afthem.actors.transformers

import com.apifortress.afthem.{Metric, SpelEvaluator}
import com.apifortress.afthem.actors.AbstractAfthemActor
import com.apifortress.afthem.messages.WebParsedRequestMessage

class ReplaceUpstreamActor(phaseId : String) extends AbstractAfthemActor(phaseId : String) {

  override def receive: Receive = {
    case msg : WebParsedRequestMessage =>
      val m = new Metric()
      val phase = getPhase(msg)
      val doReplace : Boolean = SpelEvaluator.evaluate(phase.getConfigString("expression"),
                                                          Map("msg"->msg)).asInstanceOf[Boolean]
      if(doReplace)
        msg.backend.upstream = phase.getConfigString("upstream")
      metricsLog.debug(m.toString())
      forward(msg)
  }
}
