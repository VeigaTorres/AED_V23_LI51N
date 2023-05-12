package week11Trees

import java.util.*
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.math.max

open class AedTreeSet<K>(private val comparator: Comparator<K>) : MutableSet<K> {
    private class TreeNode<K> (var key: K,
                       var left: TreeNode<K>?=null,
                       var right: TreeNode<K>?=null,
                       var parent: TreeNode<K>?=null ){
    }

    // << Variaveis de instância >>
    private var root: TreeNode<K>? = null // Raiz da àrvore

    private var count = 0
    override val size: Int get() = count

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
    private fun count(r: TreeNode<K>? = root): Int {
        if ( r != null) {
            val numberLeft= count( r.left )
            val numberRight= count(r.right)
            return numberLeft + numberRight +1
        }
        return 0
    }

    /**
     * Obter a altura da àrvore
     * A altura é a maior das alturas das subarvores mais um.
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return altura
    </K> */
    private fun height( r: TreeNode<K>?  ): Int {
        if ( r != null ) {
          //  if ( r.left == null && r.right == null )return 0
            val hl = height(r.left)
            val hr = height(r.right)
            return max( hl, hr ) +1
        }
        return -1
    }


    /**
     * Obter o nó cuja chave é key
     * @param r raiz da àrvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return nó cuja chave é key
     */
     private fun getNode(root: TreeNode<K>?, key: K): TreeNode<K>? {
        var r = root
        while ( r != null ) {
            val cmp = comparator.compare(key, r.key)
            r= when {
                cmp == 0 ->  return r
                cmp > 0 ->r.right
                else ->  r.left
            }
        }
        return null
    }

    /**
     * Adiciona um nó com chave key caso já não exista um nó com igual chave
     * @param r raiz da àrvore
     * @param key valor a procurae
     * @param <K> tipo da chave
     * @return a raiz da nova arvore
     */
    private fun addNode( key: K ): Boolean {
        var parent: TreeNode<K>? = null
        var curr= root
        var cmp = 0
        while( curr != null ){
            parent = curr
            cmp = comparator.compare(key, curr.key)
            curr = when {
                cmp == 0 -> return false
                cmp > 0 ->  curr.right
                else -> curr.left
            }
        }
        val newNode = TreeNode<K>( key )
        if ( parent == null ) root = newNode
        else {
            newNode.parent = parent
            if( cmp > 0) parent.right = newNode
            else parent.left = newNode
        }
        ++count
        return true
    }

    /**
     * Obter o nó com menor chave
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é a menor
     */
    private fun minimum( r: TreeNode<K> ): TreeNode<K> {
        var min = r
        var curr = min.left
        while (curr != null ) {
            min = curr
            curr = curr.left
        }
        return min
    }

    /**
     * Obter o nó com maior chave
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é a maior
     */
    private fun maximum(r: TreeNode<K>): TreeNode<K> {
        var max = r
        var curr = max.right
        while (curr != null) {
            max = curr
            curr = curr.right
        }
        return max
    }

    /**
     * Obter o nó cuja chave é o valor imediatamente maior
     * @param root raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é imediatsmente maior
     */
    private fun sucessor(r: TreeNode<K>): TreeNode<K>? {
        var right = r.right
        if (right!= null)
            return minimum(right)
        var parent  = r.parent
        var curr = r
        while ( parent != null  && parent.right == curr) {
            curr = parent
            parent = curr.parent
        }
        return parent
    }

    /**
     * Remover o nó z
     * @param z nó a remover
     * @param <K> tipo da chave
     * @return nó cuja chave é imediatsmente maior
     */
    private fun removeNode( z: TreeNode<K> ) {
        var rem: TreeNode<K>
        var r = z.right
        if ( r != null && z.left != null ) {
            rem = minimum( r )
            z.key = rem.key
        }
        else
            rem = z
        val parent = rem.parent
        val child = if (rem.right != null) rem.right else rem.left
        if ( child != null )  child.parent = parent
        if ( parent== null ) root = child
        else
            if ( rem == parent.right) parent.right = child
            else parent.left = child

        --count
    }

    /******************************
     * MÉTODOS QUE TÊM DE SER REDEFINIDOS NUMA COLEÇÂO MODIFICAVEL
     */
    override fun isEmpty(): Boolean = size == 0
    override fun add(element: K): Boolean = addNode( element )
    override fun remove(element: K): Boolean {
        val n = getNode( root, element )
        if ( n == null ) return false
        removeNode( n )
        return true
    }
    override fun contains(element: K): Boolean = getNode( root, element )!= null
    override fun clear() {
        root = null
        count = 0
    }
    override fun addAll(elements: Collection<out K>): Boolean {
        var b = false
        elements.forEach { if( add(it) ) b = true }
        return b
    }
    override fun containsAll(elements: Collection<K>): Boolean =
        elements.all { contains( it ) }

    override fun removeAll(elements: Collection<K>): Boolean {
        val oldSz = size
        elements.forEach { remove( it ) }
        return size != oldSz
    }
    override fun retainAll(elements: Collection<K>): Boolean =
        removeIf {!elements.contains( it) }

    override fun iterator() : MutableIterator<K> =
        object : MutableIterator<K> {
            var curr = root?.let { minimum( it ) }
            var last: TreeNode<K>?= null
            override fun hasNext(): Boolean = curr != null

            override fun next(): K =
                (curr?: throw NoSuchElementException("no more elements")).also{
                    last= curr
                    curr = sucessor( it )
                }.key


            override fun remove() {
                (last?: throw IllegalStateException("not have next() before")).let {
                    if (it.right != null && it.left!= null )
                        curr = it
                    removeNode( it )
                    last = null
                }
            }

        }

    override fun toString() =
        this.joinToString(prefix="[", postfix = "]")
    /******************************
     * MÉTODOS ACRESCENTADOS AO SET
     */
    /**
     * Obter a menor chave segundo o comparador
     * @return menor chave
     * @throws NoSuchElementException se a coleção estiver vazia
     */
    fun first(): K =
        (root?: throw NoSuchElementException("tree empty")).let{ minimum( it )}.key

    /**
     * Obter a maior chave segundo o comparador
     * @return a maior chave
     * @throws NoSuchElementException
     */
    fun last(): K =
        (root?: throw NoSuchElementException("tree empty")).let{ maximum( it )}.key

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