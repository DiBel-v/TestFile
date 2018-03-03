public class Country implements Comparable<Country>{
    private String nameCounty;
    private int user_id;
    private int count;
    private int count_uniq;
    private int sum_count;

    public Country(int user_id,int count,String nameCounty){
        this.nameCounty = nameCounty;
        this.user_id = user_id;
        this.count = count;
        this.sum_count=count;
        this.count_uniq=1;
    }

    public void setSumCount(Integer sum_count){
        this.sum_count += sum_count;
    }

    public void setCountUniq(Integer count_uniq){
        this.count_uniq += count_uniq;
    }


    public Integer getSumCount(){
        return sum_count;
    }
    public Integer getCountUniq(){
        return count_uniq;
    }

    public String getnameCountry(){
        return nameCounty;
    }

    public int getUser_id(){
        return user_id;
    }

    public int getCount(){
        return count;
    }

    public String toString(){
        return nameCounty + ";" + sum_count + ";" + count_uniq;
    }


    @Override
    public int compareTo(Country country) {
        if (this.sum_count>country.sum_count) {
            return -1;
        }
        else if (this.sum_count < country.sum_count) {
            return 1;
        }
        else if (this.count_uniq > country.count_uniq){
            return -1;
        }
        else if (this.count_uniq < country.count_uniq){
            return 1;
        }
        else return 0;
    }
}
