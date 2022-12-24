import org.apache.storm.generated.GlobalStreamId
import org.apache.storm.grouping.CustomStreamGrouping
import org.apache.storm.shade.com.google.common.collect.ImmutableList
import org.apache.storm.task.WorkerTopologyContext

import java.util
/**
 * CustomStreamGrouping has to implement two different methods
 * <p>1. <b>prepare</b> (context: WorkerTopologyContext, stream: GlobalStreamId, targetTasks: util.List[Integer])
 * <p>2. <b>chooseTask</b> (taskId: Int, values: util.List[AnyRef])
 * */
class CustomGrouping extends CustomStreamGrouping with Serializable{

  private var numOfTask :Int = _
  override def prepare(context: WorkerTopologyContext, stream: GlobalStreamId, targetTasks: util.List[Integer]): Unit ={
    this.numOfTask = targetTasks.size()
  }

  override def chooseTasks(taskId: Int, values: util.List[AnyRef]): util.List[Integer] = {
    val category : Integer = values.get(0).asInstanceOf[String].length
    ImmutableList.of(category%numOfTask)
  }


}
