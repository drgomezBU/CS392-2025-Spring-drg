interface Deque<T> {
//
    T takeout_at_beg(); // take out [x] at the front 
    void insert_at_beg(T x); // insert [x] at the front
//
    T takeout_at_end(); // take out [x] at the rear end
    void insert_at_end(T x); // insert [x] at the rear end
//
}
