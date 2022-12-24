import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.{Fields, Tuple}

import java.util

class FieldsGroupingBolt extends BaseRichBolt{

  private var memory = List[Int]()
  private var collector : OutputCollector = _

  override def prepare(conf: util.Map[String, Object], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def execute(input: Tuple): Unit = {
    val number = input.getInteger(0)
    val id = input.getSourceComponent

    if(id=="first-spout"){
      memory = memory :+ number
    }
    else{
      val str = memory.foldLeft("")(_ + " " + _)
      println(s"$number got from second-spout, accumulated from first-spout:$str")
      memory = memory.empty
    }
    collector.ack(input)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("Combined_String"))
  }

}
