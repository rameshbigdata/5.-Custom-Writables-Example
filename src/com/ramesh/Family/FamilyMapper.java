package com.ramesh.Family;
 

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FamilyMapper extends Mapper<Text,Text,IntWritable,FamilyWritable> {

    IntWritable intKey;
    FamilyWritable familyWritable;

    @Override
    protected void setup(Context context){
        System.out.println("Setup started");
        intKey = new IntWritable(0);
    }

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

        String familyData[] = value.toString().split(",");
        System.out.println("This is the value"+value.toString());
        System.out.println(familyData[0]);
        intKey.set(Integer.parseInt(familyData[2]));
        familyWritable = new FamilyWritable();
        familyWritable.setFamilyId(intKey);
        familyWritable.addFamilyMember(new Text(familyData[0]),Integer.parseInt(familyData[1]));
        try {
            context.write(intKey, familyWritable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}