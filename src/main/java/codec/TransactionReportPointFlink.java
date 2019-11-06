package codec;

import java.util.List;

public class TransactionReportPointFlink{

    private static final long serialVersionUID = 1L;

    private long mytime;

    private List<String> metrics;

    private List<Double> myvalue;

    private String TAG_report = "transaction";
    private String TAG_metricPre;
    private long TAG_interval;
    private String TAG_type;
    private String TAG_name;
    private String TAG_domain;
    private String TAG_region;
    private String TAG_group;
    private String TAG_ip;

    public TransactionReportPointFlink(String metricPre, List<String> metric, long interval, long timestamp, List<Double> value,
                                       String TAG_type, String TAG_name, String TAG_region, String TAG_group, String TAG_domain, String TAG_ip) {

        this.TAG_interval = interval;
        this.mytime = timestamp;
        this.metrics = metric;
        this.myvalue = value;
        this.TAG_type = TAG_type;
        this.TAG_name = TAG_name;
        this.TAG_domain = TAG_domain;
        this.TAG_region = TAG_region;
        this.TAG_group = TAG_group;
        this.TAG_ip = TAG_ip;
        this.TAG_metricPre = metricPre;
    }

    public long getMytime() {
        return mytime;
    }

    public void setMytime(long mytime) {
        this.mytime = mytime;
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    public List<Double> getMyvalue() {
        return myvalue;
    }

    public void setMyvalue(List<Double> myvalue) {
        this.myvalue = myvalue;
    }

    public String getTAG_report() {
        return TAG_report;
    }

    public void setTAG_report(String TAG_report) {
        this.TAG_report = TAG_report;
    }

    public String getTAG_metricPre() {
        return TAG_metricPre;
    }

    public void setTAG_metricPre(String TAG_metricPre) {
        this.TAG_metricPre = TAG_metricPre;
    }

    public long getTAG_interval() {
        return TAG_interval;
    }

    public void setTAG_interval(long TAG_interval) {
        this.TAG_interval = TAG_interval;
    }

    public String getTAG_type() {
        return TAG_type;
    }

    public void setTAG_type(String TAG_type) {
        this.TAG_type = TAG_type;
    }

    public String getTAG_name() {
        return TAG_name;
    }

    public void setTAG_name(String TAG_name) {
        this.TAG_name = TAG_name;
    }

    public String getTAG_domain() {
        return TAG_domain;
    }

    public void setTAG_domain(String TAG_domain) {
        this.TAG_domain = TAG_domain;
    }

    public String getTAG_region() {
        return TAG_region;
    }

    public void setTAG_region(String TAG_region) {
        this.TAG_region = TAG_region;
    }

    public String getTAG_group() {
        return TAG_group;
    }

    public void setTAG_group(String TAG_group) {
        this.TAG_group = TAG_group;
    }

    public String getTAG_ip() {
        return TAG_ip;
    }

    public void setTAG_ip(String TAG_ip) {
        this.TAG_ip = TAG_ip;
    }
}