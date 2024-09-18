package br.edu.satc.todolistcompose

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "descricao") val descricao: String?,
    @ColumnInfo(name = "complete") val complete: Boolean
)

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefa")
    fun getAll(): List<Tarefa>

    @Insert
    fun insertAll(vararg tarefas: Tarefa)
}

@Database(entities = [Tarefa::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun TarefaDao(): TarefaDao
}