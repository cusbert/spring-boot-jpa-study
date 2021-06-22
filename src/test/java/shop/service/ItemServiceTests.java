package shop.service;

import com.jpa.study.ch11.JpaStudyApplication;
import com.jpa.study.ch11.domain.Album;
import com.jpa.study.ch11.domain.Item;
import com.jpa.study.ch11.repository.ItemRepository;
import com.jpa.study.ch11.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JpaStudyApplication.class)
public class ItemServiceTests {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void saveTest() {
        // given
        Album item = new Album();
        item.setId(1l);
        item.setStockQuantity(1);
        item.setArtist("Steve");

        // when
        itemService.saveItem(item);

        // then
        Album findItem = (Album) itemRepository.findOne(1l);

        assertEquals(item.getId(), findItem.getId());
        assertEquals(item.getArtist(), findItem.getArtist());
    }

    public void findAllTest() {
        // given
        Item item1 = new Album();
        item1.setName("album1");

        Item item2 = new Album();
        item2.setName("album2");

        // when
        itemService.saveItem(item1);
        itemService.saveItem(item2);
        List<Item> items = itemService.findItems();

        assertEquals(2, items.size());

    }


}
