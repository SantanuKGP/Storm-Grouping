import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.{Fields, Tuple}

import java.util

@SerialVersionUID(1L)
class CustomGroupingBolt extends BaseRichBolt{

  private var collector : OutputCollector = _
  private var boltId : Int = _
  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("lengthwise-word"))
  }

  override def prepare(Conf: util.Map[String, AnyRef],
                       context: TopologyContext,
                       collector: OutputCollector): Unit = {
    this.collector = collector
    this.boltId = context.getThisTaskId

  }

  override def execute(input: Tuple): Unit = {
    val string = input.getString(0)
    println(s"Length of words: ${string.length},bolt id: $boltId "+string)
    collector.ack(input)
  }
}
