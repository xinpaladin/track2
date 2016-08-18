package cn.xjtu.track.classifier;

import cn.xjtu.track.dao.DataDetailMapper;
import cn.xjtu.track.entity.DataDetail;
import cn.xjtu.track.entity.DataItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Stack;
import java.text.DecimalFormat;

public class DTW {
    @Autowired
    private DataDetailMapper dataDetailMapper;

    public static void dtw(double a[], double b[], double dw[][], Stack<Double> w) {
        // a,b - the sequences, dw - the minimal distances matrix
        // w - the warping path
        int n = a.length, m = b.length;
        double d[][] = new double[n][m]; // the euclidian distances matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                d[i][j] = Math.abs(a[i] - b[j]);

        // determinate of minimal distance
        dw[0][0] = d[0][0];
        for (int i = 1; i < n; i++)
            dw[i][0] = d[i][0] + dw[i - 1][0];
        for (int j = 1; j < m; j++)
            dw[0][j] = d[0][j] + dw[0][j - 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dw[i - 1][j - 1] <= dw[i - 1][j]) {
                    if (dw[i - 1][j - 1] <= dw[i][j - 1]) {
                        dw[i][j] = d[i][j] + dw[i - 1][j - 1];
                    } else {
                        dw[i][j] = d[i][j] + dw[i][j - 1];
                    }
                } else {
                    if (dw[i - 1][j] <= dw[i][j - 1]) {
                        dw[i][j] = d[i][j] + dw[i - 1][j];
                    } else {
                        dw[i][j] = d[i][j] + dw[i][j - 1];
                    }
                }
            }
        }

        int i = n - 1, j = m - 1;
        double element = dw[i][j];

        // determinate of warping path
        w.push(new Double(dw[i][j]));
        do {
            if (i > 0 && j > 0)
                if (dw[i - 1][j - 1] <= dw[i - 1][j])
                    if (dw[i - 1][j - 1] <= dw[i][j - 1]) {
                        i--;
                        j--;
                    } else
                        j--;
                else if (dw[i - 1][j] <= dw[i][j - 1])
                    i--;
                else
                    j--;
            else if (i == 0)
                j--;
            else
                i--;
            w.push(new Double(dw[i][j]));
        } while (i != 0 || j != 0);
    }


    // Warp Path Distance
    public double getWarpPathDistance() {

        return 0;
    }

    // the euclidian distances matrix
    public double[][] getDistancesMatrix2(DataItem item1, DataItem item2) {
        // a,b - the sequences, dw - the minimal distances matrix
        // w - the warping path
        List<DataDetail> detail1s = dataDetailMapper.selectByItemId(item1.getId());
        List<DataDetail> detail2s = dataDetailMapper.selectByItemId(item2.getId());
        int n = detail1s.size(), m = detail2s.size();
        double d[][] = new double[n][m]; // the euclidian distances matrix
        DataDetail detail1 = null;

        DataDetail detail2 = null;
        for (int i = 0; i < n; i++) {
            detail1 = detail1s.get(i);
            for (int j = 0; j < m; j++) {

                detail2 = detail2s.get(j);
                //计算方法 待定 必须归一化 不然直接相加不合理
                //d[i][j] = Math.abs(a[i] - b[j]);
                d[i][j] = Math.abs(detail1.getLongitude() - detail2.getLongitude())  //经度
                        + Math.abs(detail1.getLatitude() - detail2.getLatitude())  //纬度
                        + Math.abs(detail1.getCourse() - detail2.getCourse())
                        + Math.abs(detail1.getRoll() - detail2.getRoll())
                        + Math.abs(detail1.getPitch() - detail2.getPitch())
                        + Math.abs(detail1.getVelE() - detail2.getVelE())
                        + Math.abs(detail1.getVelN() - detail2.getVelN())
                        + Math.abs(detail1.getLogAcce() - detail2.getLogAcce())
                        + Math.abs(detail1.getNorAcce() - detail2.getNorAcce())
                        + Math.abs(detail1.getLateralAcce() - detail2.getLateralAcce())
                ;
            }
        }
        return d;
    }


    //归一化方法
    public double normalization() {
        return 0;
    }
}
