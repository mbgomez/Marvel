package com.marvel.comicshop.repositories;

import android.content.Context;
import android.util.Log;

import com.marvel.comicshop.model.AuthorRealm;
import com.marvel.comicshop.model.Comic;
import com.marvel.comicshop.model.ComicApi;
import com.marvel.comicshop.model.ComicRealm;
import com.marvel.comicshop.model.CreatorSummary;
import com.marvel.comicshop.utils.LogJam;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by mbenitez
 */
public class DatabaseRepositoryImpl implements DatabaseRepository {

    //Local variables
    private Context context;

    /**
     * Constructor - init variables
     *
     * @param context context of the class which create it
     */
    public DatabaseRepositoryImpl(Context context) {

        this.context = context;

    }

    /**
     * Init database
     */
    public void initRealm() {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("comic_shop_db")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Write comic authors in author table
     * @param realm database
     * @param creatorList list of authors
     * @return References of the data inserted
     */
    private RealmList<AuthorRealm> writeAuthors(Realm realm, List<CreatorSummary> creatorList) {

        List<AuthorRealm> authorList = new ArrayList<>();
        AuthorRealm authorRealm;
        for (int i = 0; i < creatorList.size(); i++) {
            //Check if data exist
            authorRealm = findAuthor(realm, creatorList.get(i).getName(),
                    creatorList.get(i).getRole());
            //Create a new one if need it
            if (authorRealm == null)
                authorRealm = realm.createObject(AuthorRealm.class);
            authorRealm.setName(creatorList.get(i).getName());
            authorRealm.setRole(creatorList.get(i).getRole());
            authorList.add(authorRealm);

        }
        RealmList<AuthorRealm> authorRealmList = new RealmList<>(authorList.toArray(new AuthorRealm[authorList.size()]));

        return authorRealmList;

    }

    /**
     * Find author by name and role
     * @param realm database
     * @param name author name
     * @param role author role
     * @return Author reference
     */
    private AuthorRealm findAuthor(Realm realm, String name, String role) {

        return realm.where(AuthorRealm.class).equalTo("name", name).equalTo("role", role).findFirst();

    }

    /**
     * Write comic into comic table
     *
     * @param comic comic data
     */
    private void writeComic(ComicApi comic) {

        LogJam.d(this, "comic:" + comic);

        LogJam.d(this, "comic.getId:" + comic.getId());
        if (comic.getId().equals("17486")) {
            LogJam.d(this, "ID EXIST!");
        }
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(transactionRealm -> {
            //Check if data exist
            ComicRealm dataRealm = findComicById(transactionRealm, Integer.getInteger(comic.getId()));
            //Create a new one if need it
            if (dataRealm == null)
                dataRealm = transactionRealm.createObject(ComicRealm.class, Integer.getInteger(comic.getId()));
            //Update with the given data
            dataRealm.setTitle(comic.getTitle());
            dataRealm.setDescription(comic.getDescription());
            dataRealm.setPageNumber(comic.getPagesNumber());
            dataRealm.setThumbnail(comic.getThumbnail());
            dataRealm.setPrice(comic.getPrice());
            RealmList<AuthorRealm> authorRealmList = writeAuthors(realm, comic.getCreators().getItems());
            dataRealm.setAuthors(authorRealmList);

        });
        realm.close();

    }

    /**
     * Write a list of comics into comic table
     *
     * @param dataList list of comics
     */
    @Override
    public void writeComicList(List<ComicApi> dataList) {

        for (int i = 0; i < dataList.size(); i++) {

            writeComic(dataList.get(i));

        }

    }

    /**
     * Get a list of comic from comic table
     *
     * @return list of comic
     */
    @Override
    public List<Comic> getComicList() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ComicRealm> results = realm.where(ComicRealm.class).findAll();

        List<Comic> dataList = new ArrayList<Comic>();


        for (int i = 0; i < results.size(); i++) {

            dataList.add(results.get(i));

        }

        return dataList;

    }

    /**
     * Delete all the rows of comic table.
     */
    @Override
    public void clearComicTable() {

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(transactionRealm -> {
            RealmResults<ComicRealm> results = realm.where(ComicRealm.class).findAll();
            results.deleteAllFromRealm();
        });

        realm.close();


    }

    /**
     * Get a comic by Id
     *
     * @param realm database
     * @param id    comic id
     * @return Comic data
     */
    private ComicRealm findComicById(Realm realm, Integer id) {

        return realm.where(ComicRealm.class).equalTo("id", id).findFirst();

    }


}
