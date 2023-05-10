package week11Trees

import java.util.*
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.math.abs
import kotlin.math.max

open class AedTreeSet<K>(private val comparator: Comparator<K>) : MutableSet<K> {
    private class TreeNode<K> (var key: K,
                       var left: TreeNode<K>?=null,
                       var right: TreeNode<K>?=null,
                       var parent: TreeNode<K>?=null ){
    }

    // << Variaveis de instância >>
    private var root: TreeNode<K>? = null // Raiz da àrvore

    override val size: Int get() = count()

    /******************************
     * MÉTODOS AUXILIARES
     */

    /**
     * Contar o número de chaves - primeiro método implementado.
     * O número de elementos é a soma do número de elementos das
     * subarvores mais um.
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return número de chaves
    </K> */
    private fun count(r: TreeNode<K>? = root): Int = TODO()

    /**
     * Obter a altura da àrvore
     * A altura é a maior das alturas das subarvores mais um.
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return altura
    </K> */
    private fun height( r: TreeNode<K>?  ): Int = TODO()


    /**
     * Obter o nó cuja chave é key
     * @param r raiz da àrvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return nó cuja chave é key
     */
    private fun getNode(r: TreeNode<K>?, key: K): TreeNode<K>? {
        TODO()
    }

    /**
     * Adiciona um nó com chave key caso já não exista um nó com igual chave
     * @param r raiz da àrvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return a raiz da nova arvore
     */
    private fun addNode( key: K ): Boolean {
        TODO()
    }

    /**
     * Obter o nó com menor chave
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é a menor
     */
    private fun minimum( r: TreeNode<K> ): TreeNode<K> {
        TODO()
    }

    /**
     * Obter o nó com maior chave
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é a maior
     */
    private fun maximum(r: TreeNode<K>): TreeNode<K> {
        TODO()
    }

    /**
     * Obter o nó cuja chave é o valor imediatamente maior
     * @param root raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é imediatsmente maior
     */
    private fun sucessor(r: TreeNode<K>): TreeNode<K>? {
        TODO()
    }

    /**
     * Remover o nó z
     * @param z nó a remover
     * @param <K> tipo da chave
     * @return nó cuja chave é imediatsmente maior
     */
    private fun removeNode( z: TreeNode<K> ) {
        TODO()
    }

    /******************************
     * MÉTODOS QUE TÊM DE SER REDEFINIDOS NUMA COLEÇÂO MODIFICAVEL
     */
    override fun isEmpty(): Boolean = size == 0
    override fun add(element: K): Boolean = TODO()
    override fun remove(element: K): Boolean = TODO()
    override fun contains(element: K): Boolean = TODO()
    override fun clear() = TODO()
    override fun addAll(elements: Collection<out K>): Boolean {
        var b = false
        elements.forEach { if( add(it) ) b = true }
        return b
    }
    override fun containsAll(elements: Collection<K>): Boolean = TODO()
    override fun removeAll(elements: Collection<K>): Boolean = TODO()
    override fun retainAll(elements: Collection<K>): Boolean = TODO()
    override fun iterator() : MutableIterator<K> = TODO()

    override fun toString() = TODO() // this.joinToString(prefix="[", postfix = "]")
    /******************************
     * MÉTODOS ACRESCENTADOS AO SET
     */
    /**
     * Obter a menor chave segundo o comparador
     * @return menor chave
     * @throws NoSuchElementException se a coleção estiver vazia
     */
    fun first(): K = TODO()

    /**
     * Obter a maior chave segundo o comparador
     * @return a maior chave
     * @throws NoSuchElementException
     */
    fun last(): K = TODO()

    /**
     * Obter o comparador pela qual a coleção esta ordenada.
     * @return o comparador
     */
    fun comparator(): Comparator<in K> = comparator

    /******************************
     * MÉTODOS sobre a estrutura àrvore
     ******************************/

    /**
     * Obter a altura da árvore.
     * A altura é a maior das alturas das subarvores mais um.
     * @return a altura
     */
    fun height() = height( root )
    /**
     * Percorre a àrvore em largura.
     * Usa como auxiliar uma queue (critério de ordenação FIFO), o método:
     *  offer - adiciona
     *  poll  - remove o adicionado à mais tempo
     */
    fun transverseBreadthFirst( action: (k:K) -> Unit ) {
        TODO()
    }

    /**
     * MÉTODOS de BALANCEAMENTO
     */
    /**
     * Retorna uma lista com os elementos da àrvore adicionados de forma ordenada no
     * início da lista especificada.
     * @param root raiz da àrvore
     * @param head referência para o primeiro nó duma lista simplesmente ligada `cujos
     * nós são TreeNode e o next é o campo right do nó da àrvore.
     * @param <E>
     * @return
    </E> */
    private fun treeToList(root: TreeNode<K>?, head: TreeNode<K>? = null): TreeNode<K>? {
        TODO()
    }

    /**
     * Construir uma arvore balanceada com os primeiros size nós da lista
     * ligada ordenada.
     * @param sentinel sentinela duma lista simplesmente ligada ordenada
     * cujo next é o campo right
     * @param size
     * @return
     */
    private fun listToTree(sentinel: TreeNode<K>, size: Int ): TreeNode<K>? {
         TODO()
    }

    /**
     * Obter uma àrvore balanceada com os nós da àrvore especificada.
     * @param oldRoot raiz da àrvore
     * @param sz número de elementos da àrvore
     * @param <K> tipo da chave
     * @return a raiz de uma àrvore balanceada.
    </K> */
    private fun balance(oldRoot: TreeNode<K>, sz: Int= size): TreeNode<K>? {
        if (sz > 2) {
            // Obter a lista de nós ordenada de forma crescente
            val head = treeToList(oldRoot, null)
            // Acrescentar o nó sentinela à lista
            val sentinel = TreeNode(oldRoot.key)
            sentinel.right = head
            // Obter uma àrvore balanceada com os nós da lista.
            val newRoot = listToTree(sentinel, sz)
            if ( newRoot != null )
                newRoot.parent = null
            return newRoot
        }
        return oldRoot
    }

    /**
     * Balanceia a àrvore do TreeSet
     */
    fun balance() {
            root = root?.let{ balance(it) }
    }

    /**
     * Método auxiliar para verificar se a àrvore está balanceada. Uma àrvore está balanceada
     * se para qualquer nó a diferença das àlturas das subàrvores esquerda e direita for no
     * máximo 1.
     * @param root raiz da àrvore
     * @param <K> tipo da chave de pesquisa
     * @return  retorna a altura caso a árvore esteja balanceada ou -1 caso não esteja.
    </K> */
    private fun isBal(root: TreeNode<K>?): Int = TODO()

    /**
     * Verificar se a àrvore do TreeSet está balanceada.
     * @return  true se a árvore estiver balanceada, caso contrário false.
     */
    fun isBalancing(): Boolean = isBal(root) >= 0
}