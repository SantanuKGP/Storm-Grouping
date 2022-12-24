import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.{Fields, Tuple}

import java.util

@SerialVersionUID(1L)
class FirstTopologyBolt extends BaseRichBolt{

//  private var memory = List[Int]()
//  private var memory_str = "["
  private var collector : OutputCollector = _
  private var contextId = ""
  private var taskId = -1

  override def prepare(conf: util.Map[String, Object], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
    this.contextId = context.getThisComponentId
    this.taskId = context.getThisTaskId
  }

  override def execute(input: Tuple): Unit = {

    val num = input.getInteger(0)
    val key = input.getString(1)
    val user = input.getString(2)
    println(s"$key with $user from $contextId with id $taskId: $num")

    collector.ack(input)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("Combined_String"))
  }
}
