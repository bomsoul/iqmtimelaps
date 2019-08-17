package sample.model;

public class TimeLap {
    private int time1;
    private int time2;

    public TimeLap(int time1, int time2) {
        this.time1 = time1;
        this.time2 = time2;
    }

    public int diffential(){
        if(this.time1 > this.time2){
            return (86400000-this.time1)+this.time2;
        }
        return this.time2-this.time1;
    }

    public String msToTime(int duration) {
        long millis = duration % 1000;
        long second = (duration / 1000) % 60;
        long minute = (duration / (1000 * 60)) % 60;
        long hour = (duration / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
//        String milliseconds = (duration%1000)/100 + "";
//        String seconds = ((duration/1000)%60) + "";
//        String minutes = ((duration/(1000*60))%60) + "";
//        String hours = ((duration/(1000*60*60))%24) + "";
//
//        hours = (Integer.parseInt(hours) < 10) ? "0" + hours : hours;
//        minutes = (Integer.parseInt(minutes) < 10) ? "0" + minutes : minutes;
//        seconds = (Integer.parseInt(seconds) < 10) ? "0" + seconds : seconds;
        return time;
        //return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
    }

    @Override
    public String toString() {
        return msToTime(this.time1)+"\n       to\n"+msToTime(this.time2);
    }
}
