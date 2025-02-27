import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcompose.Internal.MyGamesRepository
import com.example.roomcompose.Object.Games
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyGamesViewModel(private val repository: MyGamesRepository) : ViewModel() {
    /**
     * State yang menyimpan semua game yang diambil dari repository.
     * Data ini dikelola dalam state yang akan diperbarui secara otomatis.
     */
    val allGames = repository.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    /**
     * Inisialisasi ViewModel dengan memulai sinkronisasi data dari Firestore.
     */
    init {
        repository.syncFromFirestore() // Start Firestore sync when ViewModel initializes
    }

    /**
     * Menyisipkan game baru ke dalam repository.
     *
     * @param game Game yang akan dimasukkan ke dalam database.
     */
    fun insertGame(game: Games) {
        viewModelScope.launch {
            repository.insertGames(game)
        }
    }

    /**
     * Menghapus semua game yang tersimpan dalam database.
     */
    fun deleteAllGames() {
        viewModelScope.launch {
            repository.deleteAllGames()
        }
    }
}
