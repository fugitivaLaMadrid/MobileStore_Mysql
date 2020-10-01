package mobileStore.ui;

import mobileStore.database.Queries;

public class MobileApp {
      public static void main(String[] args) {
          Queries queries = new Queries();
          queries.doConnection();
    }
}
