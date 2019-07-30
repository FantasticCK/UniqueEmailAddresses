package com.CK;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] s = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        Solution solution = new Solution();
        System.out.println(solution.numUniqueEmails(s));
    }
}

class Solution {
    Map<String, List<String>> domainToLocal = new HashMap<>();

    public int numUniqueEmails(String[] emails) {
        int count = 0;
        if (emails.length == 0) return 0;
        for (int email = 0; email < emails.length; email++) {
            String[] split = emails[email].split("@");
            String domain = split[1];
            String local = split[0].replaceAll("[+].*", "").replaceAll("[.]", "");
            if (!domainToLocal.containsKey(domain)) {
                domainToLocal.put(domain, new ArrayList<>());
                domainToLocal.get(domain).add(local);
                count++;
            } else {
                if (domainToLocal.get(domain).contains(local)) continue;
                domainToLocal.get(domain).add(local);
                count++;
            }
        }
        return count;
    }
}

//HashSet
class Solution2 {
    public int numUniqueEmails(String[] emails) {
        Set<String> seen = new HashSet();
        for (String email: emails) {
            int i = email.indexOf('@');
            String local = email.substring(0, i);
            String rest = email.substring(i);
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll("\\.", "");
            seen.add(local + rest);
        }

        return seen.size();
    }
}