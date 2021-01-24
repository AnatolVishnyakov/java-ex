package ru.avishnyakov.javaex.stream;

import org.junit.jupiter.api.Test;
import ru.avishnyakov.javaex.model.Artist;
import ru.avishnyakov.javaex.model.Track;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.avishnyakov.javaex.TestData.album;
import static ru.avishnyakov.javaex.TestData.musicians;

public class StreamHomework {
    private int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }

    @Test
    public void testSumNumbers() {
        int sum = addUp(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));

        assertEquals(45, sum);
    }

    @Test
    public void testMusiciansFrom() {
        final List<String> artistFrom = album.getMusicians()
                .filter(artist -> artist.getName().equals("The architects"))
                .map(artist -> artist.getName() + " " + artist.getNationality())
                .collect(Collectors.toList());

        assertEquals(asList("The architects UK"), artistFrom);
    }

    @Test
    public void testLimitTracks() {
        final List<Track> tracks = album.getTracks()
                .limit(3)
                .collect(Collectors.toList());

        assertEquals(3, tracks.size());
    }

    @Test
    public void testRefactoringOutsideIterToInsideIter() {
        {
            int totalMembers = 0;
            for (Artist artist : musicians) {
                final Stream<Artist> members = artist.getMembers();
                totalMembers += members.count();
            }
            assertEquals(0, totalMembers);
        }
        {
            final long totalMembers = musicians.stream()
                    .flatMap(Artist::getMembers)
                    .count();
            assertEquals(0, totalMembers);
        }
    }

    @Test
    public void testCountLowerCharInString() {
        final String expression = "Mama myla ramu!";
        final long result = expression.chars()
//                .peek(value -> System.out.println(value + " " + (char) value))
                .filter(value -> value >= 'a' && value <= 'z')
                .count();
        assertEquals(11, result);
    }

    @Test
    public void testFindMaxStringLength() {
        final List<String> strings = asList(
                "Не плюй в колодец, пригодится воды напиться",
                "Не делай неприятностей кому-либо, иначе в будущем сам можешь лишиться поддержки",
                "Под лежачий камень и вода не течет",
                "Без труда не выловишь и рыбку из пруда.",
                "Дело не сдвинется с места, если ничего не предпринимать"
        );

        // Stream
        final Optional<String> result = strings.stream()
                .max(Comparator.comparing(s -> s.chars()
                                .mapToObj(value -> (char) value)
                                .filter(ch -> ch >= 'а' && ch <= 'я')
                                .count()
                        )
                );

        // Iterator
        String foundString = "";
        for (int i = 0, maxStringCount = 0; i < strings.size(); i++) {
            int count = 0;
            for (int j = 0; j < strings.get(i).length(); j++) {
                final char ch = strings.get(i).charAt(j);
                if (ch >= 'а' && ch <= 'я') {
                    count++;
                }
            }
            if (count > maxStringCount) {
                maxStringCount = count;
                foundString = strings.get(i);
            }
        }

        assertEquals(strings.get(1), foundString);
        assertEquals(strings.get(1), result.get());
    }
}
