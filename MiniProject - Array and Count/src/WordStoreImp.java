class WordStoreImp implements WordStore {

    Frequency[] store;
    int initial;
    int currentLength = 0;


    public WordStoreImp(int num) {
        initial = num;
        store = new Frequency[initial];
    }

    @Override
    public void add(String word) {
        int positionOfWord = position(word);
        if (positionOfWord != -1) {
            store[positionOfWord].setFrequency(store[positionOfWord].getFrequency() + 1);
        } else if (currentLength < store.length) {
            store[currentLength] = new Frequency(word, 1);
            currentLength++;
        } else {
            increaseSize();
            store[currentLength] = new Frequency(word, 1);
            currentLength++;
        }
        return;
    }


    @Override
    public int count(String word) {
        for (int i = 0; i < currentLength; i++) {
            if (store[i].getWord().equals(word)) {
                return store[i].getFrequency();
            }
        }
        return 0;
    }


    @Override
    public void remove(String word) {
        int positionOfWord = position(word);
        if (positionOfWord != -1) {
            store[positionOfWord] = store[currentLength - 1];
            currentLength -= 1;
        }
        return;
    }

    private int position(String word) {
        for (int i = 0; i < currentLength; i++) {
            if (store[i].getWord().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    private void increaseSize() {
        Frequency[] newStore = new Frequency[currentLength * 2];
        for (int i = 0; i < currentLength; i++) {
            newStore[i] = store[i];
        }
        store = newStore;
        return;
    }
}

