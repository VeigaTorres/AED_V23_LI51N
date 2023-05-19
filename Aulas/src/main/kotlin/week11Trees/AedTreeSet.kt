package week11Trees

import java.util.LinkedList
import java.util.Queue
import kotlin.Comparator
import kotlin.NoSuchElementException
import kotlin.math.max
import kotlin.math.min

open class AedTreeSet<K>(private val comparator: Comparator<K>) : MutableSet<K> {
    private class TreeNode<K> (
        var key: K,
        var left: TreeNode<K>?=null,
        var right: TreeNode<K>?=null,
        var parent: TreeNode<K>?=null ) {
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
     * @param root raiz da àrvore
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
     * @param r raiz da àrvore
     * @param <K> tipo da chave
     * @return nó cuja chave é imediatsmente maior
     */
    private fun sucessor(r: TreeNode<K>): TreeNode<K>? {
        val right = r.right
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
        val r = z.right
        if ( r != null && z.left != null ) {
            rem = minimum( r )
            z.key = rem.key
        }
        else {
            rem = z
        }
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
    override fun addAll(elements: Collection<K>): Boolean {
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
        if ( root == null ) return head
        var newHead = treeToList( root.right, head)
        root.right = newHead
        newHead = root
        return treeToList(root.left, newHead)
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
        if ( size == 0 ) return null
        val n= size/2
        val treeLeft= listToTree( sentinel, n )
        val root = sentinel.right
        root?.let{
            it.left = treeLeft // Faltava na aula
            treeLeft?.parent = root
            sentinel.right = it.right
            it.right = listToTree( sentinel, size-n -1)
            it.right?.parent = root
        }
        return root
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
    private fun isComplete(root: TreeNode<K>?): Int? {
        if ( root == null ) return 0
        val hl = isComplete(root.left)
        if ( hl != null ) {
            val hr = isComplete(root.right)
            if ( hl == hr) return hl + 1
        }
        return null
    }

    /**
     * Verificar se a àrvore do TreeSet está balanceada.
     * @return  true se a árvore estiver balanceada, caso contrário false.
     */
    fun isCompelete(): Boolean = isComplete(root) != null

    /**
     * Percorre a àrvore em largura.
     * Usa como auxiliar uma queue (critério de ordenação FIFO), o método:
     *  offer - adiciona
     *  poll  - remove o adicionado à mais tempo
     */
    fun transverseBreadthFirst( action: (k:K) -> Unit ) {
        root?.let {
            val q: Queue< TreeNode<K> > = LinkedList<TreeNode<K>>() // Do java.util
            q.offer( it )
            while( !q.isEmpty()) {
                val r= q.poll()
                action( r.key )
                if ( r.left != null ) q.offer( r.left )
                r.right?.let{q.offer( it) }
            }
        }
    }

    /**
     * Obter o enésimo elemento da arvore.
     * @param n número do elemento a obter
     * @return o enésima elemento.
     */
    private data class Result<K>( var n: Int, var node: TreeNode<K>?)
    fun nesimo(n: Int) : K {
       val result = Result<K>( n, null)
        nesimo( root, result)
        return (result.node ?: throw NoSuchElementException("")).key
    }

    private fun nesimo(root: TreeNode<K>?, result: Result<K>) {
        if (root == null) return
        nesimo( root.left, result)
        --result.n
        if ( result.n == 0 ) {
            result.node =  root
        }
        if ( result.node == null ) nesimo(root.right, result)

    }

    /**
     * Dada a árvore binária pesquisa com raíz root, retorna a menor profundidade
     * da árvore. A menor profundidade é o número de nós existente no caminho mais
     * curto entre o nó raíz e um dos nós folha.
     * @param root raiz da àrvore
     * @return  a menor profundidade.
     */
    private fun findMinimumDepth(root:TreeNode<K>):Int{
        val r = root.right
        val l = root.left
        if ( r== null && l == null ) return 0
        val hr = if ( r!= null ) findMinimumDepth( r )
                 else Int.MAX_VALUE
        val hl = if( l != null) findMinimumDepth( l)
                 else Int.MAX_VALUE
        return min(hl, hr) + 1
    }

    fun findMinimumDepth():Int {
        (root ?: return -1).let {
            return findMinimumDepth(it)
        }
    }
    /**
     * Dada a árvore binária com raíz root, de valores inteiros positivos,
     * retorna o maior inteiro presente na árvore que seja menor ou igual a
     * determinado valor k ou null caso não exista.
     * @param root raiz da àrvore
     * @param k valor maior ou igual
     * @return  maior inteiro presente na árvore que seja menor ou igual.
     */
    private fun higher( root:TreeNode<K>?, k:K, cmp: (K,K)-> Int ): K? {
        var r = root
        var greater: K? = null
        while ( r != null ) {
            if ( cmp(k, r.key) <0) {
                r = r.left
            }
            else if ( cmp(k, r.key)>0) {
                greater = r.key
                r= r.right
            }
            else return r.key
        }
        return greater
    }
    fun higher( k:K, cmp: (K,K)-> Int ): K? =
        (root?: throw NoSuchElementException("")).let {
            higher( it, k, cmp)
        }
    /**
     * Dada a árvore binária com raíz root, verifica se a árvore satisfaz
     * a propriedade da soma dos nós filhos. Para uma árvore satisfazer a
     * propriedade da soma dos nós filhos, o valor presente em cada nó
     * (exceto os nós folha) deverá ser igual à soma dos valores presentes
     * nos seus nós filhos (esquerdo e direito).
     * @param root raiz da àrvore
     * @return  true se a árvore satisfaz a propriedade da soma dos nós filhos.
     */
    private fun isChildrenSum(root:TreeNode<Int>?):Boolean {
        TODO()
    }

    /**
     * Dada a árvore binária pesquisa com raíz root, sem repetições, e
     * dois inteiros a e b, em que a<b, verifica se eles ocorrem na árvore
     * em nós irmãos. Dois nós são irmãos se ocorrem no mesmo nível e
     * têm o mesmo nó pai.
     * @param root raiz da àrvore
     * @a valor inteiro
     * @b valor inteiro
     * @return  true se a e b eles ocorrem na árvore em nós irmãos.
     */
    private fun areSiblingsInBST(root:TreeNode<Int>?,a:Int, b:Int):Boolean {
        TODO()
    }
}