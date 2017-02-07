package cscie97.asn1;

import java.util.*;

/**
 * @author khaled
 */
public class Generator {
    public static void main(String[] args) {
        List<String> people = Arrays.asList("Phoenix", "Jimena", "Karla", "Elian", "Marissa", "Gabrielle", "Evie", "Matilda", "Payton", "Jeffrey", "Yareli", "Eli"
        , "Andy", "Aaron", "Armando", "Karson", "Nadia", "Jovan", "Erin", "Terry", "Grayson", "Celia", "Alexzander", "Cannon"
        , "Kera", "Candis", "Camila", "Monica", "Merry", "Kathlene", "Keven", "Caitlyn", "Dominga", "Vanita", "Myrtice", "Maxwell", "Hanna", "Heide", "Perry", "Harlan", "Towanda", "Nikki", "Jeffie", "Damon");
        List<String> sports = Arrays.asList("football", "tennis", "cards", "gymnastic", "basketball", "Wrestling", "Weightlifting", "Skiing", "Badminton");
        List<String> countries = Arrays.asList("Chile", "Mauritania", "Trinidad", "India", "Senegal", "Bahamas", "Syria", "Gambia", "Malawi", "France", "Germany"
        , "Vanuatu", "Kiribati", "Colombia", "Nauru", "Congo", "Palau", "Eritrea", "Guatemala", "Turkmenistan");
        List<String> music = Arrays.asList("Pop", "Rock", "Chillout", "Indie", "Blue", "Country", "Folk", "Classic", "Jazz", "Punk");
        List<String> station = Arrays.asList("Spotify", "iTunes", "Google", "Rdio", "Pandora");

        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Set<Integer> picked = new HashSet<>();
            int index = random.nextInt(people.size());
            if (!picked.contains(index)) {
                picked.add(index);
                String person = people.get(index);
                int index2 = random.nextInt(people.size());
                if (index != index2) {
                    System.out.println(person.concat(" friend_with ").concat(people.get(index2)).concat("."));
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            Set<Integer> picked = new HashSet<>();
            int index = random.nextInt(people.size());
            if (!picked.contains(index)) {
                picked.add(index);
                String person = people.get(index);
                int index2 = random.nextInt(sports.size());
                System.out.println(person.concat(" plays ").concat(sports.get(index2)).concat("."));
                if (Math.random() < 0.5) {
                    int index3 = random.nextInt(sports.size());
                    if (index3 != index2) {
                        System.out.println(person.concat(" plays ").concat(sports.get(index3)).concat("."));
                    }
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            Set<Integer> picked = new HashSet<>();
            int index = random.nextInt(people.size());
            if (!picked.contains(index)) {
                picked.add(index);
                String person = people.get(index);
                int index2 = random.nextInt(music.size());
                System.out.println(person.concat(" listen_to ").concat(music.get(index2)).concat("."));
                if (Math.random() < 0.5) {
                    int index3 = random.nextInt(music.size());
                    if (index3 != index2) {
                        System.out.println(person.concat(" listen_to ").concat(music.get(index3)).concat("."));
                    }
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            Set<Integer> picked = new HashSet<>();
            int index = random.nextInt(people.size());
            if (!picked.contains(index)) {
                picked.add(index);
                String person = people.get(index);
                int index2 = random.nextInt(station.size());
                System.out.println(person.concat(" subscribes_to ").concat(station.get(index2)).concat("."));
            }
        }

        station.forEach(s -> {
            Set<Integer> picked = new HashSet<>();
            while (picked.size() != 4) {
                int index = random.nextInt(music.size());
                if (!picked.contains(index)) {
                    picked.add(index);
                    String mu = music.get(index);
                    System.out.println(s.concat(" streams ").concat(mu).concat("."));
                }
            }
        });

        station.forEach(s -> {
            Set<Integer> picked = new HashSet<>();
            while (picked.size() != 13) {
                int index = random.nextInt(countries.size());
                if (!picked.contains(index)) {
                    picked.add(index);
                    String c = countries.get(index);
                    System.out.println(s.concat(" available_in ").concat(c).concat("."));
                }
            }
        });

        people.forEach(p -> System.out.println(p.concat(" born_in ").concat(countries.get(random.nextInt(countries.size()))).concat(".")));
        people.forEach(p -> System.out.println(p.concat(" studied_in ").concat(countries.get(random.nextInt(countries.size()))).concat(".")));
    }
}
