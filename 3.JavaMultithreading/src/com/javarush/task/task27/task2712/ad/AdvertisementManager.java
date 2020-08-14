package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    public final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> list = new ArrayList<>();
        for (Advertisement adv : storage.list()) {
            if (adv.getHits() > 0) list.add(adv);
        }
        Collections.sort(list, (o1, o2) -> o2.getDuration() - o1.getDuration());
        int size = list.size() - 1;
        long maxShows = getMaxSell(size, timeSeconds, list);

        List<List<Advertisement>> lists = new ArrayList<>();

        for (int i = 0; i <= size; i++) {
            List<Advertisement> temp = new ArrayList<>();
            int timeTemp = list.get(i).getDuration();
            long sellTemp = list.get(i).getAmountPerOneDisplaying();
            temp.add(list.get(i));

            if (timeTemp > timeSeconds) {
                continue;
            }

            if (sellTemp == maxShows && timeTemp <= timeSeconds) listSum(lists, temp);

            for (int j = i + 1; j <= size; j++) {
                timeTemp += list.get(j).getDuration();
                sellTemp += list.get(j).getAmountPerOneDisplaying();
                temp.add(list.get(j));

                if (timeTemp > timeSeconds) {
                    temp.remove(temp.size() - 1);
                    timeTemp -= list.get(j).getDuration();
                    sellTemp -= list.get(j).getAmountPerOneDisplaying();
                    continue;
                }

                if (sellTemp == maxShows && timeTemp <= timeSeconds) listSum(lists, temp);

            }
        }

        Comparator<List<Advertisement>> listComparatorTime = (o1, o2) -> (sumTime(o1) == sumTime(o2)) ? o1.size() - o2.size() : sumTime(o2) - sumTime(o1);
        Collections.sort(lists, listComparatorTime);

        List<Advertisement> optimalList = new ArrayList<>();
        for (Advertisement adv : lists.get(0)) {
            optimalList.add(adv);
        }

        Collections.sort(optimalList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying() ?
                        (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration()) -
                                (o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration()) :
                        o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        });

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(optimalList, maxShows, sumTime(optimalList)));

        for (Advertisement ad : optimalList) {
            ConsoleHelper.writeMessage(String.format("%size is displaying... %d, %d", ad.getName(), ad.getAmountPerOneDisplaying(),
                    (int) (ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration())));
            ad.revalidate();
        }


    }

    private int sumTime(List<Advertisement> list) {
        return list.stream().mapToInt(Advertisement::getDuration).sum();
    }

    private static void listSum(List<List<Advertisement>> lists, List<Advertisement> temp) {
        if (!lists.contains(temp)) {
            lists.add(temp);
        }
    }

    private long getMaxSell(int i, int allTime, List<Advertisement> list) {
        if (i < 0) return 0;
        if (list.get(i).getDuration() > allTime) {
            return getMaxSell(i - 1, allTime, list);
        } else {
            return Math.max(getMaxSell(i - 1, allTime, list)
                    , getMaxSell(i - 1, allTime - list.get(i).getDuration(), list) +
                            list.get(i).getAmountPerOneDisplaying());
        }
    }
}
