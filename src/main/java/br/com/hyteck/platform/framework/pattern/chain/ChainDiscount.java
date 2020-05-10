//package br.com.hyteck.platform.framework.pattern.chain;
//
//import br.com.hyteck.platform.entity.Cart;
//import br.com.hyteck.platform.framework.pattern.IDiscount;
//
//import static java.util.Objects.nonNull;
//
//public abstract class ChainDiscount {
//
//    private IDiscount next;
//
//    /**
//     * Builds chains of AbstractDiscount objects.
//     */
//    public IDiscount linkWith(IDiscount next) {
//        this.next = next;
//        return next;
//    }
//
//    /**
//     * Runs check on the next object in chain or ends traversing if we're in
//     * last object in chain.
//     * <p>
//     * execute Coupon discount every last time
//     */
//    public Cart verifyNext(Cart cart) {
//        applyDiscount(cart);
//        if (next != null) {
//            return next.verifyNext(cart);
//        } else {
//            if (nonNull(cart.getCoupon())) {
//                return cart.getCoupon().applyDiscount(cart);
//            }
//            return cart;
//        }
//    }
//}
