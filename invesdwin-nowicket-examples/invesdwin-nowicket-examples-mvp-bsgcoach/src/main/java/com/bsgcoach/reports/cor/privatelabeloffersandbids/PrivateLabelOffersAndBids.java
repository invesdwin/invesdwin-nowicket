package com.bsgcoach.reports.cor.privatelabeloffersandbids;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import com.bsgcoach.web.request.CompanyRegion;

import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.bean.AValueObject;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class PrivateLabelOffersAndBids extends AValueObject {

    private final CompanyRegion companyRegion;

    //Private-Label Pairs Offered ----------------------------                N.A.
    //    E-A
    //    A-P
    //    L.A.
    private Decimal privateLabelPairsOffered;
    //Private-    N.A.--------------  Bid Won (0/1)
    private Boolean privateLabelBidWon01;
    //Label       Production from-----------      N.A.
    //Bids          (pairs)       E-A
    //    A-P
    //    L.A.
    //
    //E-A--------------   Bid Won (0/1)
    //Production from-----------      N.A.
    //(pairs)       E-A
    //    A-P
    //    L.A.
    //
    //A-P--------------   Bid Won (0/1)
    //Production from-----------      N.A.
    //(pairs)       E-A
    //    A-P
    //    L.A.
    //
    //L.A.--------------  Bid Won (0/1)
    //Production from-----------      N.A.
    //(pairs)       E-A
    //    A-P
    //    L.A.
    private final Map<CompanyRegion, Decimal> privateLabelProductionPairsFrom = new HashMap<CompanyRegion, Decimal>();

    //Private-Label Bid Prices ----------------------------               N.A.
    //    E-A
    //    A-P
    //    L.A.
    private Decimal privateLabelBidPrice;

    //    Private-Label S/Q Rating of Pairs Offered ----------------------------              N.A.
    //    E-A
    //    A-P
    //    L.A.
    private Decimal privateLabelSqRatingOfPairsOffered;

    public PrivateLabelOffersAndBids(final CompanyRegion companyRegion) {
        this.companyRegion = companyRegion;
    }

    public Decimal getPrivateLabelPairsOffered() {
        return privateLabelPairsOffered;
    }

    public void setPrivateLabelPairsOffered(final Decimal privateLabelPairsOffered) {
        this.privateLabelPairsOffered = privateLabelPairsOffered;
    }

    public Boolean getPrivateLabelBidWon01() {
        return privateLabelBidWon01;
    }

    public void setPrivateLabelBidWon01(final Boolean privateLabelBidWon01) {
        this.privateLabelBidWon01 = privateLabelBidWon01;
    }

    public Decimal getPrivateLabelBidPrice() {
        return privateLabelBidPrice;
    }

    public void setPrivateLabelBidPrice(final Decimal privateLabelBidPrices) {
        this.privateLabelBidPrice = privateLabelBidPrices;
    }

    public CompanyRegion getCompanyRegion() {
        return companyRegion;
    }

    public Decimal getPrivateLabelProductionPairsFrom(final CompanyRegion companyRegion) {
        return privateLabelProductionPairsFrom.get(companyRegion);
    }

    public void setPrivateLabelProductionPairsFrom(final CompanyRegion companyRegion, final Decimal value) {
        Assertions.assertThat(privateLabelProductionPairsFrom.put(companyRegion, value)).isNull();
    }

    public Decimal getPrivateLabelSqRatingOfPairsOffered() {
        return privateLabelSqRatingOfPairsOffered;
    }

    public void setPrivateLabelSqRatingOfPairsOffered(final Decimal privateLabelSqRatingOfPairsOffered) {
        this.privateLabelSqRatingOfPairsOffered = privateLabelSqRatingOfPairsOffered;
    }

}
