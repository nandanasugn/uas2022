public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    if(test_input==null)
        test_input=context.getConfiguration().get("test_input").split("\\,");
        String[] input=value.toString().split("\\,");
        for(int j=0;j<input.length;j++){
            if(j==input.length-1){
                if(outcome_count.containsKey(input[j]))
                    outcome_count.put(input[j], outcome_count.get(input[j])+1);
                else
                    outcome_count.put(input[j], (double) 1);
            } else{
                if(input[j].contentEquals(test_input[j])){
                    if(!inputs.containsKey(j+","+input[j]))
                        inputs.put(j+","+input[j], 0);
                        if(features_count.containsKey(j+","+input[j]+"|"+input[input.length-1]))
                            features_count.put(j+","+input[j]+"|"+input[input.length-1], features_count.get(j+","+input[j]+"|"+input[input.length-1])+1);
                        else
                            features_count.put(j+","+input[j]+"|"+input[input.length-1], (double) 1);
                }
            }
        }
    ++count;
}
