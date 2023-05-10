package week11Tree

class TreeValue<E>(val value: E,
                   var left: TreeValue<E>? = null,
                   var right: TreeValue<E>? = null)

fun <E> transverse( root: TreeValue<E>? ) {
    if (root != null ) {
      transverse( root.left )
      print("${root.value} ")
      transverse( root.right )
    }
}

fun main() {
    val left = TreeValue(13, TreeValue(8), TreeValue(14))
    val right = TreeValue(19, right = TreeValue(33))
    val tree = TreeValue(15, left, right)
    transverse( tree )
}