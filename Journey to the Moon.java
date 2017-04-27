//Lucien Mendela

import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //total number of astronauts
    String[] line = br.readLine().split(" ");
    final int N = Integer.parseInt(line[0]);
    
    //Initialize individual astronaut's compatriots
    final List<List<Integer>> compatriots = new ArrayList<List<Integer>>(N);
    for(int i = 0; i < N; ++i){
      compatriots.add(new ArrayList<Integer>());
    }
    
    //retrieve each astronaut's compatriots
    for(short L = Short.parseShort(line[1]); L > 0; --L){
      line = br.readLine().split(" ");
      final int A = Integer.parseInt(line[0]);
      final int B = Integer.parseInt(line[1]);
      compatriots.get(A).add(B);
      compatriots.get(B).add(A);
    }
    
    //Visit each "country" 
    boolean[] isVisited = new boolean[N];
    List<Integer> countrySizes = new ArrayList<Integer>();
    for(int i = 0; i < N; ++i){
      int countrySize = 0;
      final Queue<Integer> q = new ArrayDeque<Integer>();
      q.add(i);
      do {
        int astronautId = q.poll();
        if(!isVisited[astronautId]){
          ++countrySize;
          isVisited[astronautId] = true;
          q.addAll(compatriots.get(astronautId));
        }
      } while (!q.isEmpty());
      if(countrySize > 0){
        countrySizes.add(countrySize);
      }
    }
    
    //Get number of pairs if possible
    long numPairs = 0L;
    long numPartners = N;
    for(int countrySize : countrySizes){
      numPairs += countrySize * (numPartners -= countrySize);
    }
    
    //Print output
    System.out.print(numPairs);
  }
}
