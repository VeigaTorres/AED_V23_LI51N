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

    // << Variaveis de inst�ncia >>
    private var root: TreeNode<K>? = null // Raiz da �rvore

    override val size: Int get() = count()

    /******************************
     * M�TODOS AUXILIARES
     */

    /**
     * Contar o n�mero de chaves - primeiro m�todo implementado.
     * O n�mero de elementos � a soma do n�mero de elementos das
     * subarvores mais um.
     * @param r raiz da �rvore
     * @param <K> tipo da chave
     * @return n�mero de chaves
    </K> */
    private fun count(r: TreeNode<K>? = root): Int = TODO()

    /**
     * Obter a altura da �rvore
     * A altura � a maior das alturas das subarvores mais um.
     * @param r raiz da �rvore
     * @param <K> tipo da chave
     * @return altura
    </K> */
    private fun height( r: TreeNode<K>?  ): Int = TODO()


    /**
     * Obter o n� cuja chave � key
     * @param r raiz da �rvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return n� cuja chave � key
     */
    private fun getNode(r: TreeNode<K>?, key: K): TreeNode<K>? {
        TODO()
    }

    /**
     * Adiciona um n� com chave key caso j� n�o exista um n� com igual chave
     * @param r raiz da �rvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return a raiz da nova arvore
     */
    private fun addNode( key: K ): Boolean {
        TODO()
    }

    /**
     * Obter o n� com menor chave
     * @param r raiz da �rvore
     * @param <K> tipo da chave
     * @return n� cuja chave � a menor
     */
    private fun minimum( r: TreeNode<K> ): TreeNode<K> {
        TODO()
    }

    /**
     * Obter o n� com maior chave
     * @param r raiz da �rvore
     * @param <K> tipo da chave
     * @return n� cuja chave � a maior
     */
    private fun maximum(r: TreeNode<K>): TreeNode<K> {
        TODO()
    }

    /**
     * Obter o n� cuja chave � o valor imediatamente maior
     * @param root raiz da �rvore
     * @param <K> tipo da chave
     * @return n� cuja chave � imediatsmente maior
     */
    private fun sucessor(r: TreeNode<K>): TreeNode<K>? {
        TODO()
    }

    /**
     * Remover o n� z
     * @param z n� a remover
     * @param <K> tipo da chave
     * @return n� cuja chave � imediatsmente maior
     */
    private fun removeNode( z: TreeNode<K> ) {
        TODO()
    }

    /******************************
     * M�TODOS QUE T�M DE SER REDEFINIDOS NUMA COLE��O MODIFICAVEL
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
     * M�TODOS ACRESCENTADOS AO SET
     */
    /**
     * Obter a menor chave segundo o comparador
     * @return menor chave
     * @throws NoSuchElementException se a cole��o estiver vazia
     */
    fun first(): K = TODO()

    /**
     * Obter a maior chave segundo o comparador
     * @return a maior chave
     * @throws NoSuchElementException
     */
    fun last(): K = TODO()

    /**
     * Obter o comparador pela qual a cole��o esta ordenada.
     * @return o comparador
     */
    fun comparator(): Comparator<in K> = comparator

    /******************************
     * M�TODOS sobre a estrutura �rvore
     ******************************/

    /**
     * Obter a altura da �rvore.
     * A altura � a maior das alturas das subarvores mais um.
     * @return a altura
     */
    fun height() = height( root )
    /**
     * Percorre a �rvore em largura.
     * Usa como auxiliar uma queue (crit�rio de ordena��o FIFO), o m�todo:
     *  offer - adiciona
     *  poll  - remove o adicionado � mais tempo
     */
    fun transverseBreadthFirst( action: (k:K) -> Unit ) {
        TODO()
    }

    /**
     * M�TODOS de BALANCEAMENTO
     */
    /**
     * Retorna uma lista com os elementos da �rvore adicionados de forma ordenada no
     * in�cio da lista especificada.
     * @param root raiz da �rvore
     * @param head refer�ncia para o primeiro n� duma lista simplesmente ligada `cujos
     * n�s s�o TreeNode e o next � o campo right do n� da �rvore.
     * @param <E>
     * @return
    </E> */
    private fun treeToList(root: TreeNode<K>?, head: TreeNode<K>? = null): TreeNode<K>? {
        TODO()
    }

    /**
     * Construir uma arvore balanceada com os primeiros size n�s da lista
     * ligada ordenada.
     * @param sentinel sentinela duma lista simplesmente ligada ordenada
     * cujo next � o campo right
     * @param size
     * @return
     */
    private fun listToTree(sentinel: TreeNode<K>, size: Int ): TreeNode<K>? {
         TODO()
    }

    /**
     * Obter uma �rvore balanceada com os n�s da �rvore especificada.
     * @param oldRoot raiz da �rvore
     * @param sz n�mero de elementos da �rvore
     * @param <K> tipo da chave
     * @return a raiz de uma �rvore balanceada.
    </K> */
    private fun balance(oldRoot: TreeNode<K>, sz: Int= size): TreeNode<K>? {
        if (sz > 2) {
            // Obter a lista de n�s ordenada de forma crescente
            val head = treeToList(oldRoot, null)
            // Acrescentar o n� sentinela � lista
            val sentinel = TreeNode(oldRoot.key)
            sentinel.right = head
            // Obter uma �rvore balanceada com os n�s da lista.
            val newRoot = listToTree(sentinel, sz)
            if ( newRoot != null )
                newRoot.parent = null
            return newRoot
        }
        return oldRoot
    }

    /**
     * Balanceia a �rvore do TreeSet
     */
    fun balance() {
            root = root?.let{ balance(it) }
    }

    /**
     * M�todo auxiliar para verificar se a �rvore est� balanceada. Uma �rvore est� balanceada
     * se para qualquer n� a diferen�a das �lturas das sub�rvores esquerda e direita for no
     * m�ximo 1.
     * @param root raiz da �rvore
     * @param <K> tipo da chave de pesquisa
     * @return  retorna a altura caso a �rvore esteja balanceada ou -1 caso n�o esteja.
    </K> */
    private fun isBal(root: TreeNode<K>?): Int = TODO()

    /**
     * Verificar se a �rvore do TreeSet est� balanceada.
     * @return  true se a �rvore estiver balanceada, caso contr�rio false.
     */
    fun isBalancing(): Boolean = isBal(root) >= 0
}