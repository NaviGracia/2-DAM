import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}



@Composable
fun TaskCard(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clip(RoundedCornerShape(12.dp)),
        elevation = 6.dp,
        backgroundColor = Color.LightGray
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${task.name}", style = MaterialTheme.typography.h6)
            Text("Description: ${task.description}")
            Text("Due Date: ${task.dueDate}")
            Text("Priority: ${task.priority}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text("Delete", color = Color.White)
            }
        }
    }
}
