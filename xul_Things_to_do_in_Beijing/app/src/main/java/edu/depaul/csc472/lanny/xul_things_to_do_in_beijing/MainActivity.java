package edu.depaul.csc472.lanny.xul_things_to_do_in_beijing;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ListActivity {

    private static final String TAG = "MyActivity";
    private static final int RATING = 100; // request code
    private Things selectedThing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ThingsAdapter(this));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position=" + position + " id=" + id + " " + THINGS[position].getName());
        selectedThing = THINGS[position];
        Intent intent = new Intent(MainActivity.this, RatingsActivity.class);
        intent.putExtra("ThingsName", selectedThing.getName());
        intent.putExtra("ThingsDescription", selectedThing.getLDes());
        intent.putExtra("ThingsIcon", Things.getIconResource(selectedThing.getType()));
        intent.putExtra("ThingsRating", selectedThing.getRating());
        startActivityForResult(intent, RATING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult"); // + data.getFloatExtra("WineRating", 4));
        if (requestCode == RATING) {
            if (resultCode == RESULT_OK && data != null) {
                selectedThing.setRating(data.getFloatExtra("ThingsRating", 4));
                ((ThingsAdapter) getListAdapter()).notifyDataSetChanged();
            }
        }
    }

    // More efficient version of adapter
    static class ThingsAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Map<Things.Type, Bitmap> icons;

        ThingsAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            icons = new HashMap<Things.Type, Bitmap>();
            icons.put(Things.Type.Museums, BitmapFactory.decodeResource(context.getResources(), R.drawable.museums));
            icons.put(Things.Type.Sites, BitmapFactory.decodeResource(context.getResources(), R.drawable.sites));
            icons.put(Things.Type.Parks, BitmapFactory.decodeResource(context.getResources(), R.drawable.park));
            icons.put(Things.Type.Food, BitmapFactory.decodeResource(context.getResources(), R.drawable.food));
        }

        @Override
        public int getCount() {
            return THINGS.length;
        }

        @Override
        public Object getItem(int i) {
            return THINGS[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {
                row = inflater.inflate(R.layout.things_list, parent, false);
                holder = new ViewHolder();
                holder.icon = row.findViewById(R.id.image);
                holder.name = row.findViewById(R.id.text1);
                holder.description = row.findViewById(R.id.text2);
                holder.rating = row.findViewById(R.id.text3);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Things things = THINGS[position];
            holder.name.setText(things.getName());
            holder.description.setText(things.getSDes());
            holder.icon.setImageBitmap(icons.get(things.getType()));
            holder.rating.setText(things.getRating() + " stars");
            return row;
        }

        static class ViewHolder {
            TextView name;
            TextView description;
            ImageView icon;
            TextView rating;
        }
    }

    private static final Things[] THINGS = {
            new Things("Mutianyu Great Wall",
                    Things.Type.Sites,
                    "Mutianyu Road, Huairou District, Beijing, China",
                    "In 1368 AD, Mutianyu Great Wall was built by Xu Da who is the main general for Zhuyuanzhang in the Great Wall ruins of Northern Qi Dynasty. Linked to Gubeikou in the east and Juyongguan in the west,the section of the Great Wall is the military hub defensing of the capitalsince ancient times. There are several famous watchtowers such as Zhengguantai, Dajiaolou, Yingfeidaoyang,Jiankou and Beijingjie, which has profound historical and cultural values.",
                    5.0f),
            new Things("Temple of Heaven (Tiantan Park)",
                    Things.Type.Sites,
                    "Tiantan Road, Dongcheng District, Beijing 100050, China",
                    "Built in 1420 with a total area of 270 acres, this is the largest building for religious worship in China, which was originally used by the Ming and Qing emperors to pay homage to Heaven and to pray for a year of rich harvest.",
                    4.5f),
            new Things("Forbidden City-The Palace Museum",
                    Things.Type.Sites,
                    "No.4 Jingshanqian Street, Dongcheng District, Beijing 100009, China",
                    "Consisting of more than 9,000 rooms and spread over 250 acres, this huge palace complex was built in the 15th century and later extensively renovated and restored during the Qing Dynasty in the 18th century.",
                    4.8f),
            new Things("Summer Palace (Yiheyuan)",
                    Things.Type.Sites,
                    "19 Xin-jian-gong-men Rd, Haidian District, Beijing 100084, China",
                    "Once a summer retreat for emperors, this 290-acre park is still a retreat for the crowd-weary tourist, who can relax here or stroll around ancient pavilions, mansions, temples, bridges and huge lake, stopping occasionally at a shop or tea-house.",
                    4.6f),
            new Things("Gate of Heavenly Peace (Tian'an Men)",
                    Things.Type.Sites,
                    "North of Tian'anmen square, Dongcheng District, Beijing, China",
                    "In 1949, after communist forces ultimately triumphed over Chiang Kai-Shek's nationalist army, Mao Zedong proclaimed the the existence of the People's Republic of China from atop Tiananmen (or Gate of Heavenly Peace). Today, a huge portrait of Mao hangs from the the enormous red-painted structure, gazing down towards Tiananmen Square. For almost seven hundred years, its five arched gateways have connected the outside world with the Forbidden City. Originally, the largest central archway was reserved only for the emperor, while court mandarins had to be content with entering via one of the four smaller archways.",
                    4.5f),
            new Things("National Museum Of China",
                    Things.Type.Museums,
                    "No.16 East Chang'an Street, Dongcheng District, Beijing 100006, China",
                    "This museum features changing exhibits related to political themes such as the Opium Wars, the founding of the Communist Party, the Sino-French and Sino-Japanese Wars, the Japanese War, the 1911 Revolution and the social unrest of 1989.",
                    4.5f),
            new Things("Beijing Capital Museum",
                    Things.Type.Museums,
                    "No.16 Fuxingmenwai Main Street, Xicheng District, Beijing 100045, China",
                    "Fantastic building filled with exhibitions and amazing displays. 5 floors of unique Chinese art not to be missed.",
                    4.3f),
            new Things("Jingshan Park",
                    Things.Type.Parks,
                    "No.44 Jingshanxi Street, Xicheng District, Beijing 100009, China",
                    "Located on the highest point in Beijing City, this park was built in 1179 during the Jin Dynasty and today provides visitors with sweeping views of the Forbidden City located below.",
                    4.6f),
            new Things("Beihai Park",
                    Things.Type.Parks,
                    "No.1 Wenjin Street, Xicheng District, Beijing 100034, China",
                    "Kubla Khan in 1260 took up residence in this palace, which today is a 168-acre park filled with historic buildings, restaurants and a lake.",
                    4.6f),
            new Things("The Imperial Garden",
                    Things.Type.Parks,
                    "Inside The Palace of Museum, Dongcheng District, Beijing 100006, China",
                    "Beautiful gardens, VERY old trees, cool rock formations",
                    4.0f),
            new Things("Happy Valley of Beijing",
                    Things.Type.Parks,
                    "Xiaowujibei Road, Dongsihuan, Chaoyang District, Beijing 100023, China",
                    "Looking for breathtaking roller coasters and adventure you can't go home without spending a whole day in this theme park. Easily accessible by metro and as soon as you enter the park, fun begins.",
                    4.5f),
            new Things("Beijing Zoo",
                    Things.Type.Parks,
                    "No.137 Xizhimenwai Main Street, Xicheng District, Beijing 100044, China",
                    "Pandas, pandas and pandas!! You won't regret it!",
                    4.5f),
            new Things("Dadong Roast Duck",
                    Things.Type.Food,
                    "Chinese, Asian, Vegetarian Friendly",
                    "1-2 Nanxincang Guoji Dasha, 22A Dongsishitiao, Dongcheng district, Beijing 100007, China",
                    4.2f),
            new Things("Jin Ding Xuan(DiTan)",
                    Things.Type.Food,
                    "Chinese, Asian, Healthy, Diner, Vegetarian Friendly",
                    "No.77 Hepinglixi Street, Dongcheng District | No.77 Hepinglixi Street, Dongcheng District, Beijing 100000, China",
                    4.3f)
    };
}
